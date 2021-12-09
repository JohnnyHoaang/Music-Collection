package src;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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
            muS = new MusicStudio(creds);
            System.out.println("entered db");
;
            
            muS.createContributor("Dom", "cuna", "C003", "R002", "RE04");
            muS.printAllRecContributor();
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