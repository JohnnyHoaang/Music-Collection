package src;

import src.entities.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class MusicStudio {
    private Credentials creds;
    private Connection con;

    public MusicStudio(Credentials creds) throws SQLException{
        this.creds = creds;
        this.con = connectToDB(creds.getUser(), creds.getPassword());
        if (this.con == null){
            throw new IllegalArgumentException("Connection cannot be null");
        }        
    }

    public Credentials getCreds() {
        return creds;
    }

    public Connection connectToDB(String username, String password) throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca",
                username, password );
    }
    //PLEASE FIX THAT DOMENICO OR ASHLEY IM TOO LAZY AND DONE WITH LIFE
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

    //Inserting the tables (Not Testing bc VPN sucks)
    
    //Testing the Procedures
    public void createContributor(Contributor contributor, String roleid, String recid) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_CONTRIBUTOR(?,?,?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,contributor.getCfirst());
        statementCall.setString(2,contributor.getClast());
        statementCall.setString(3, contributor.getContributorId());
        statementCall.setString(4, roleid);
        statementCall.setString(5, recid);
        statementCall.execute();
    }

    public void updateRecording(String recid, Date date, double duration, double offset) throws SQLException{
        String callProcedure = "{call updatepkg.UPDATE_RECORDING(?,?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,recid);
        statementCall.setDate(2, date);
        statementCall.setDouble(3, duration);
        statementCall.setDouble(4, offset);
        statementCall.execute();
    }

    public void createCollection(String collectionid, String albumname) throws SQLException{
        String callProcedure = "{call CREATE_COLLECTION(?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1, collectionid);
        statementCall.setString(2, albumname);
        statementCall.execute();
    }


    //Delete data from the table
    public void deleteSong(String albumId) throws SQLException{
        String delSong = "{call deletepkg.DELETE_SONG(?)}";
        CallableStatement statementCall = this.con.prepareCall(delSong);
        statementCall.setString(1, albumId);
        statementCall.execute();
    }
    public void deleteContributor(String contributorId) throws SQLException{
        String delContr = "{call deletepkg.DELETE_CONTRIBUTOR(?)}";
        CallableStatement statementCall = this.con.prepareCall(delContr);
        statementCall.setString(1, contributorId);
        statementCall.execute();
    }
    public void deleteCollection(String collectionId) throws SQLException{
        String delColl = "{call deletepkg.DELETE_COLLECTION(?)}";
        CallableStatement statementCall = this.con.prepareCall(delColl);
        statementCall.setString(1, collectionId);
        statementCall.execute();
    }
    public void deleteRecording(String recordingid) throws SQLException{
        String delColl = "{call deletepkg.DELETE_RECORDING(?)}";
        CallableStatement statementCall = this.con.prepareCall(delColl);
        statementCall.setString(1, recordingid);
        statementCall.execute();
    }
    
    public void updateTable(String table, String column, String givenId, String newData) throws SQLException{
        String id= " ";
        switch(table){
            case "album":
            id = "albumid";
            break;
            case "collection":
            id = "collectionid";
            break;
            case "recording":
            id = "recid";
            break;
            case "contributor":
            id = "contributorid";
            break;
            case "contributor_role":
            id = "roleid";
            break;
        }
        if (column.equals("duration") || column.equals("offset")){
            String sql = "update "+table + " set " + column + " = ? where " + id + " = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setDouble(1, Double.parseDouble(newData));
            prep.setString(2, givenId);
            prep.executeUpdate();
        }
        else if(column.equals("date") || column.equals("rec_date")){
            String sql = "update "+table + " set " + column + " = ? where " + id + " = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setDate(1, Date.valueOf(newData));
            prep.setString(2, givenId);
            prep.executeUpdate();
        }
        else {
            String sql = "update "+table + " set " + column + " = ? where " + id + " = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setString(1, newData);
            prep.setString(2, givenId);
            prep.executeUpdate();
        }
    }

    public void closeConnection() throws SQLException{
        this.con.close();
    }
    //Date.valueOf("1997-03-10");
}
