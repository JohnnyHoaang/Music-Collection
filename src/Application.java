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

        try
        {
            Credentials creds = new Credentials(user, password);
            MusicStudio muS = new MusicStudio(creds);

            //Testing the LOGS
            Logs gimmeLogs = muS.getUserLogs();
            System.out.println(gimmeLogs);

            System.out.println("entered db");
    
        }catch(
        Exception e)
        {
            e.printStackTrace();
        }
        finally{
            //muS.closeConnection();
        }
    }
   
  
}