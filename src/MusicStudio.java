package src;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class MusicStudio {
    private Credentials creds;
    private Connection con;

    public MusicStudio(Credentials creds) throws SQLException{
        this.creds = creds;
        this.con = getConnections(creds.getUser(), creds.getPassword());
        
    }

    public Credentials getCreds() {
        return creds;
    }

    public Connection getConnections(String username, String password) throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca",
                username, password );
    }


    public void closeConnection() throws SQLException{
        this.con.close();
    }
}
