package src;

import java.io.Console;
import java.sql.Connection;
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
        try
        {
            Credentials creds = new Credentials(user, password);
            Scanner scanner = new Scanner(System.in);
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
                 if(result.equals("1")){
                    //Print all song in the library here
                    System.out.println("Which song do you want to view?");
                    var song = scanner.next();
                    System.out.println("Here is the information of the song you chose");
                   //print all informantion of the song here
   

                 }
                 else if(result.equals("2")){
                    System.out.println("Please fill all the information of the song you wanna add:");
                    //loop through the song prop arrays here
                    
                 }
                 else if(result.equals("3")){
                    System.out.println("Which data do you want to update?");
                    var answer = scanner.next();
                    System.out.println("Which song do you want to update?");
                    var song = scanner.next();
                    //Log the change here 

                 }
                 else if(result.equals("4")){
                    System.out.println("Which song do you want to delete :");
                    var answer = scanner.next();
                    //Log all the change here
                 }
                 else if(result.equals("5")){
                     //Print all user log 
                     open = false;
                }
            }

            muS = new MusicStudio(creds);
            System.out.println("entered db");

            
            muS.testcreateContributor("NICO", "LAS", "C002", "R004", "RE06");
            muS.printAllRecContributor();

            muS.deleteSong();
            //Calling Procedures
            //Getting the contributor object
            Logs logs = muS.getUserLogs();
            System.out.println(logs);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            muS.closeConnection();
        }
    }
   
  
}