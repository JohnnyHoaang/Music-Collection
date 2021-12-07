package src;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.logging.annotations.Log;
import oracle.jdbc.proxy.annotation.Pre;

public class MusicStudio {
    private Credentials creds;
    private Connection con;

    public MusicStudio(Credentials creds) throws SQLException{
        this.creds = creds;
        this.con = connectToDB(creds.getUser(), creds.getPassword());
        
    }

    public Credentials getCreds() {
        return creds;
    }

    public Connection connectToDB(String username, String password) throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca",
                username, password );
    }

    public Logs getUserLogs() throws SQLException{
        Logs logMessage = new Logs(this.creds.getUser());
        
        String retrieveLogs = "SELECT * FROM USER_LOGS WHERE USERNAME = ?";
        PreparedStatement prep = this.con.prepareStatement(retrieveLogs);
        // System.out.println(this.creds.getUser());
        prep.setString(1, this.creds.getUser());

        ResultSet rs = prep.executeQuery();

        while(rs.next()){
            logMessage.addTrack(rs.getString("changes"));
        }
        return logMessage;
    }

    //print all info from (any table), maybe create objects 
    public void printAllRecContributor() throws SQLException{
        String contributors = "SELECT * FROM CONTRIBUTOR";
        PreparedStatement prep = this.con.prepareStatement(contributors);
        var rs = prep.executeQuery();
        while(rs.next()){
            System.out.println("Contributor id: " + rs.getString("contributorid")+ " Fullname: " + rs.getString("c_first") + " " + rs.getString("c_last"));
        }
    }
    public void printAllRecording() throws SQLException{
        String recordings = "SELECT * FROM RECORDING";
        PreparedStatement prep = this.con.prepareStatement(recordings);
        ResultSet rs = prep.executeQuery();
        while(rs.next()){
            System.out.println("Rec id:" + rs.getString("recid")+ " date:" + rs.getDate("rec_date") + " duration:" + rs.getString("duration") + " offset:" + rs.getString("offset"));
        }
    }
    public void printAllCollection() throws SQLException{
        String collection = "SELECT * FROM COLLECTION";
        PreparedStatement prep = this.con.prepareStatement(collection);
        ResultSet rs = prep.executeQuery();
        while(rs.next()){
            System.out.println("Collection Id: " + rs.getString("collectionid")+ " Name: " + rs.getString("name"));
        }
    }
    public void printAllAlbums() throws SQLException{
        String album = "SELECT * FROM ALBUM";
        PreparedStatement prep = this.con.prepareStatement(album);
        ResultSet rs = prep.executeQuery();
        while(rs.next()){
            System.out.println("Album Id: " + rs.getString("albumid")+ "Name: " + rs.getString("name") +" Category: " + rs.getString("category") + " pubdate: " + rs.getDate("pubdate") + " collection ID: " + rs.getString("collectionid"));
        }
    }



    public void printRecContributor() throws SQLException{
        String contributors = "SELECT * FROM USER_LOGS WHERE USERNAME = ?";
        PreparedStatement prep = this.con.prepareStatement(contributors);
    }

    public void closeConnection() throws SQLException{
        this.con.close();
    }
}
