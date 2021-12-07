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
        // String user ="A2036759";
        // String password = "Cow4life";
        try
        {
            Credentials creds = new Credentials(user, password);
            MusicStudio muS = new MusicStudio(creds);
            System.out.println("entered db");

            //Testing the LOGS
            // Logs gimmeLogs = muS.getUserLogs();
            // System.out.println(gimmeLogs);
            muS.printAllRecContributor();
            //Calling Procedures
            //Getting the contributor object


            //Testing the insert statements
            // muS.insertRecContributor();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            //muS.closeConnection();
        }
    }
   
  
}