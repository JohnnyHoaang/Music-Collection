package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MusicConnections {
    private Credentials creds;
    private Connection con;

    public MusicConnections(Credentials creds) throws SQLException{
        this.creds = creds;
        this.con = getConnection(creds.getUser(), creds.getPassword());
    }    

    public Credentials getCreds() {
        return creds;
    }

    public static Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca",
                username, password);
    }

    public void closeConnection() throws SQLException{
        this.con.close();
    }
}
