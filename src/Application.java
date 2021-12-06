package src;

import java.io.Console;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        Console console = System.console();

        String user = console.readLine("Gimme yo damn username");
        String password = new String(console.readPassword("Gimme yo passward"));
        
        MusicStudio muS = null;
        try
        {
            Credentials creds = new Credentials(user, password);
            muS = new MusicStudio(creds);
    
        }catch(
        Exception e)
        {
            e.printStackTrace();
        }
        finally{
            muS.closeConnection();
        }
    }
  
}