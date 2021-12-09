package src;

import src.entities.*;
import java.io.Console;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {
        Console console = System.console();

        String user = console.readLine("Username: ");
        String password = new String(console.readPassword("Password: "));
        //sample to use your password /username, DONT GIVE it TO DIRK
        // String user ="";
        // String password = "";
        MusicStudio muS = null;
        Scanner scanner = null;
        try
        {
            Credentials creds = new Credentials(user, password);
            muS = new MusicStudio(creds);
            scanner = new Scanner(System.in);
            boolean open = true;
            while(open){
                 System.out.println("Welcome to our program");
                 System.out.println("Select from the following options:");
                 System.out.println("1) VIEW SONG");
                 System.out.println("2) ADD SONG");
                 System.out.println("3) UPDATE SONG");
                 System.out.println("4) DELETE SONG");
                 System.out.println("5) EXIT");
                 var result = scanner.next();
                 //View song
                 if(result.equals("1")){
                    //Print all song in the library here
                    muS.printAllAlbums();
                    System.out.println("Which song do you want to view? Pick the song id please...");
                    String songid = scanner.next();
                    //print song info method
                    System.out.println("Here is the information of the song you chose");
                   //print all informantion of the song here
                    //muS.printAllRecContributors();
                    //muS.printAllRecordings();
                    //muS.printAllCollection();
                    //mus.printAllAlbums();

                 }
                 //Add song
                 else if(result.equals("2")){
                    System.out.println("Please fill all the information of the song you wanna add:");
                    //create contributors and recording
                    //loop through the song prop arrays here
                    System.out.println("Creating contributors:");
                    String cid = console.readLine("Enter contributor id: ");
                    String clast = console.readLine("Enter contributor last name: ");
                    String cfirst = console.readLine("Enter contributor firstname: ");
                    //Print all the role id right here
                    String roleid = console.readLine("Enter Role ID: ");
                    String recid = console.readLine("Enter RECID: ");
                    Contributor contributor = new Contributor(cid,cfirst,clast);
                    System.out.println("");
                    //creates an object contributor
                    muS.createContributor(contributor,roleid,recid);
                    String date = console.readLine("Enter date: ");
                    System.out.println("Enter duration: ");
                    double duration = scanner.nextDouble();
                    System.out.println("Enter offset: ");
                    double offset = scanner.nextDouble();
                    muS.updateRecording(recid,Date.valueOf(date),duration,offset);
                    //2
                    //create collection
                    //print all collection id
                    //3
                    //create album, to create album you need recordings
                    //print all recordings, print all collections
                 }
                 else if(result.equals("3")){
                    System.out.println("Which data do you want to update?");
                    var answer = scanner.next();
                    System.out.println("Which song do you want to update?");
                    var song = scanner.next();
                    //muS.updateTable(String table, String column, String givenId, String newData)
                    //Log the change here 

                 }
                 else if(result.equals("4")){
                    System.out.println("Which song do you want to delete :");
                    var answer = scanner.next();
                    //Log all the change here
                    //muS.deleteSong(albumid);
                    //which contributor to delete
                    //muS.deleteContributor(contributorid);
                    //which collection to delete
                    //muS.deleteCollection(collectionid)
                 }
                 else if(result.equals("5")){
                     //Print all user log 
                     open = false;
                }
            }
            System.out.println("entered db");

            
            //muS.createContributor("NICO", "LAS", "C002", "R004", "RE06");
            
            //Calling Procedures
            //Getting the contributor object
            muS.updateTable("contributor", "c_first", "C002", "Dan");
            muS.printAllRecContributor();
            Logs logs = muS.getUserLogs();
            System.out.println(logs);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            scanner.close();
            muS.closeConnection();
        }
    }
    // public static void addContributor(Contributor contributor, Connection conn) throws SQLException{
    //     if(conn == null|| contributor == null){
    //                 throw new IllegalArgumentException("cannot be null");
    //             }
    //             String insertQuery = "Insert into contributor(contributorid,cfirst,clast) values (?,?,?)";
    //             var insertPrep = conn.prepareStatement(insertQuery);
    //             insertPrep.setString(1,contributor.getContributorId());
    //             insertPrep.setString(2,contributor.getCfirst());
    //             insertPrep.setString(3,contributor.getClast());
                
    //             var result = insertPrep.executeUpdate();
    //             if(result == 0){
    //                 throw new SQLException("Did not insert anything");
    //             }
    // }
    // public static void addOrder(Order order, Connection conn)throws SQLException {
    //     if(conn == null|| order == null){
    //         throw new IllegalArgumentException("cannot be null");
    //     }
    //     String insertQuery = "Insert into orders(order#,orderdate) values (?,?)";
    //     var insertPrep = conn.prepareStatement(insertQuery);
    //     insertPrep.setInt(1,order.getOrderNumber());
    //     insertPrep.setDate(2,order.getOrderDate());
    //     var result = insertPrep.executeUpdate();
    //     if(result == 0){
    //         throw new SQLException("Did not insert anything");
    //     }

    // }
  
}