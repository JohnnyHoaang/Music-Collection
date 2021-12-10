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
                 System.out.println("2) ADD DATA");
                 System.out.println("3) UPDATE DATA");
                 System.out.println("4) DELETE DATA");
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
                    // muS.printAllCollection();
                    //mus.printAllAlbums();

                    //Collection
                    //

                 }
                 //Add song
                 else if(result.equals("2")){
                    System.out.println("Please fill all the information of the song you wanna add:");
                    //create contributors and recording
                    //loop through the song prop arrays here
                    System.out.println("Creating contributors:");
                    muS.printAllRecContributor();
                    String cid = console.readLine("Pick an UNIQUE contributor id not on the list above: ");
                    String clast = console.readLine("Enter contributor last name: ");
                    String cfirst = console.readLine("Enter contributor firstname: ");
                    Contributor contributor = new Contributor(cid,cfirst,clast);
                    muS.createContributor(contributor);
                    System.out.println("Contributor created: " + contributor);
                    System.out.println("Creating recording:");
                    String recid = console.readLine("Enter RECID: ");
                    String date = console.readLine("Enter date: ");
                    System.out.println("Enter duration: ");
                    double duration = scanner.nextDouble();
                    System.out.println("Enter offset: ");
                    double offset = scanner.nextDouble();
                    Recording rec = new Recording(recid,Date.valueOf(date), duration, offset);
                    muS.createRecording(rec);
                    System.out.println("Recording created: " + rec);
                    //creates an object contributor
                    System.out.println("Link this recording to an existing contributor");
                    System.out.println("Here are your contributor choices!");
                    System.out.println("-----------------------------------------------------------------------");
                    muS.printAllRecContributor();
                    
                    String contributorid = console.readLine("Give the contributor id: ");
                    System.out.println("Here are your role choices!");
                    muS.printAllRoles();
                    String roleid = console.readLine("Give the role id: ");
                    Role role = muS.getRole(roleid);
                    Contributor con = muS.getContributor(contributorid);
                    ContributorRec contributorRec = new ContributorRec(con, rec, role);
                    muS.createontributorRec(contributorRec);
                    System.out.println("Successfully linked song and role to contributor!" + '\n' +contributorRec);
                    // muS.updateRecording(recid,Date.valueOf(date),duration,offset);
                    //2
                    //create collection
                    //print all collection id
                    //3
                    //create album, to create album you need recordings
                    //print all recordings, print all collections
                 }

                 //update song
                 else if(result.equals("3")){
                     //print all tables
                    muS.printAllTables();
                    String table = console.readLine("Which table do you want to update? ");
                    //print all ids of that table
                    String givenId = console.readLine("Enter id of the row that you want to change: ");
                    muS.printAllIDRowsFromTable(table);
                    String column = console.readLine("Which field do you want to update? ");
                    String newData = console.readLine("Enter new field value: ");
                    muS.updateTable(table,column,givenId,newData);
                    //missing the parse string to double 
                    //Log the change here 

                 }
                 //delete dsta
                 else if(result.equals("4")){
                    System.out.println("What data do you wish to delete? Here are the choices");
                    System.out.println("1) DELETE THE WHOLE SONG");
                    System.out.println("2) DELETE CONTRIBUTOR");
                    System.out.println("3) DELETE COLLECTION");
                    System.out.println("4) DELETE RECORDING");
                    String answer = scanner.nextLine();
                    switch(answer){
                        case "1":
                        System.out.println("Which song do you want to delete : ");
                        String albumid = console.readLine("Enter the albumd id: ");
                        muS.deleteSong(albumid);
                        break;

                        case "2":
                        //case 2: delete contributor
                        System.out.println("Which contributor do you want to delete : ");
                        String contributorid = console.readLine("Enter the contributor id: ");
                        muS.deleteContributor(contributorid);
                        break;

                        case "3":
                         //case 3: delete collection
                        System.out.println("Which collection do you want to delete?");
                        String collectionid = console.readLine("Enter the collection id: ");
                        //which collection to delete
                        muS.deleteCollection(collectionid);

                        case "4":
                        //case 4: delete recording
                        System.out.println("Which recording do you want to delete?");
                        String recid = console.readLine("Enter the recid: ");
                        //which collection to delete
                        muS.deleteRecording(recid);
                    }
                 }
                 else if(result.equals("5")){
                     //Print all user log 
                     open = false;
                }
            }
            System.out.println("entered db");
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
}