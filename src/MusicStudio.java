package src;

import src.entities.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public Album getAlbum(String albumid) throws SQLException{
        Album alb = null;

        String album = "SELECT * FROM ALBUM WHERE albumid = ?";
        PreparedStatement prep = this.con.prepareStatement(album);
        prep.setString(1, albumid);

        ResultSet rs = prep.executeQuery();
        while(rs.next()){
            alb = new Album(rs.getString("albumid"), rs.getString("title"), rs.getString("category"), 
            rs.getDate("pubdate"), rs.getString("collectionid"),rs.getString("market"), rs.getString("label"));
        }
        return alb;
    }

    public Collection getCollection(String collectionid) throws SQLException{
        Collection col = null;

        String collection = "SELECT * FROM COLLECTION WHERE collectionid = ?";
        PreparedStatement prep = this.con.prepareStatement(collection);
        prep.setString(1, collectionid);

        ResultSet rs = prep.executeQuery();
        while(rs.next()){
            col = new Collection(rs.getString("collectionid"), rs.getString("name"));
        }
        return col;
    }


    public Contributor getContributor(String contributorid) throws SQLException{
        Contributor contr = null;
        String contributor = "SELECT * FROM CONTRIBUTOR WHERE contributorid = ?";
        PreparedStatement prep = this.con.prepareStatement(contributor);
        prep.setString(1, contributorid);
        ResultSet rs = prep.executeQuery();


        while(rs.next()){
            contr = new Contributor(rs.getString("contributorid"), rs.getString("c_first"), rs.getString("c_last"));
        }
        return contr;
    }
    public Role getRole(String roleid) throws SQLException{
        Role role = null;
        String contributor = "SELECT * FROM CONTRIBUTOR_ROLE WHERE roleid = ?";
        PreparedStatement prep = this.con.prepareStatement(contributor);
        prep.setString(1, roleid);
        ResultSet rs = prep.executeQuery();
        while(rs.next()){
            role = new Role(rs.getString("roleid"), rs.getString("rolename"));
        }
        return role;
    }
    public Recording getRecording(String recordingid) throws SQLException{
        Recording recording = null;
        String contributor = "SELECT * FROM RECORDING WHERE recid = ?";
        PreparedStatement prep = this.con.prepareStatement(contributor);
        prep.setString(1, recordingid);
        ResultSet rs = prep.executeQuery();
        while(rs.next()){
            recording = new Recording(rs.getString("recid"), rs.getDate("date"), rs.getDouble("duration"), rs.getDouble("offset"));
        }
        return recording;
    }


    public void printAllIDRowsFromTable(String table) throws SQLException{
        String id = "";

        //Refactor into method
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

        String printID = "SELECT "+id+" FROM "+table;
        PreparedStatement prep = this.con.prepareStatement(printID);
        ResultSet rs = prep.executeQuery();

        while(rs.next()){
            System.out.println(rs.getString(1));
        }

    }


    public void printAllRoles() throws SQLException{
        String role = "SELECT * FROM CONTRIBUTOR_ROLE";
        PreparedStatement prep = this.con.prepareStatement(role);
        ResultSet rs = prep.executeQuery();

        ArrayList<Role> roles = new ArrayList<>();

        while(rs.next()){
            roles.add(new Role(rs.getString("roleid"), rs.getString("rolename")));         
        }
        
        for(Role rol : roles){
            System.out.println(rol);
            System.out.println("---------------------------");
        }
    }

    //print all info from (any table), maybe create objects
    //Storing the contributors into an object
    public void printAllRecContributor() throws SQLException{
        String contributor = "SELECT * FROM CONTRIBUTOR";
        PreparedStatement prep = this.con.prepareStatement(contributor);
        ResultSet rs = prep.executeQuery();

        ArrayList<Contributor> contributors = new ArrayList<>();

        while(rs.next()){
            contributors.add(new Contributor(rs.getString("contributorid"), rs.getString("c_first"), rs.getString("c_last")));  
        }

        for(Contributor contr : contributors){
            System.out.println(contr);
            System.out.println("---------------------------");
        }
    }

    //Storing the recordings into an object
    public void printAllRecording() throws SQLException{
        String recording = "SELECT * FROM RECORDING";
        PreparedStatement prep = this.con.prepareStatement(recording);
        ResultSet rs = prep.executeQuery();

        ArrayList<Recording> recordings = new ArrayList<>();

        while(rs.next()){
            
            recordings.add(new Recording(rs.getString("recid"), rs.getDate("date"), rs.getDouble("duration"), rs.getDouble("offset")));
        }

        for(Recording rec : recordings){
            System.out.println(rec);
            System.out.println("---------------------------");
        }
    }

    //Storing the collections into an object
    public void printAllCollection() throws SQLException{
        String collection = "SELECT * FROM COLLECTION";
        PreparedStatement prep = this.con.prepareStatement(collection);
        ResultSet rs = prep.executeQuery();

        ArrayList<Collection> collections = new ArrayList<>();

        while(rs.next()){  
            collections.add(new Collection(rs.getString("collectionid"), rs.getString("name")));
        }

        for(Collection col : collections){
            System.out.println(col);
            System.out.println("---------------------------");
        }
    }

    //Storing the songs into an object
    public void printAllAlbums() throws SQLException{
        String album = "SELECT * FROM ALBUM";
        PreparedStatement prep = this.con.prepareStatement(album);
        ResultSet rs = prep.executeQuery();

        ArrayList<Album> albums = new ArrayList<>();

        while(rs.next()){
            albums.add(new Album(rs.getString("albumid"), rs.getString("title"), rs.getString("category"), 
                rs.getDate("pubdate"), rs.getString("collectionid"),rs.getString("market"), rs.getString("label")));
        }
        for(Album alb : albums){
            System.out.println(alb);
            System.out.println("---------------------------");
        }
    }
    
    public void printAllTables(){
        System.out.println("\n" + "Here are the tables:");
        System.out.println("contributor, recording, album, collection");
        System.out.println("contributor_role, contributor_rec, compilation"); 
        System.out.println();
    }

    // public void printRecContributor() throws SQLException{
    //     String contributors = "SELECT * FROM USER_LOGS WHERE USERNAME = ?";
    //     PreparedStatement prep = this.con.prepareStatement(contributors);
    // }




    
    //Testing the Procedures
    public void createContributor(Contributor contributor) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_CONTRIBUTOR(?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,contributor.getCfirst());
        statementCall.setString(2,contributor.getClast());
        statementCall.setString(3, contributor.getContributorId());
        statementCall.execute();
    }

    public void createRecording(Recording recording) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_RECORDING(?,?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,recording.getRecordingId());
        statementCall.setDate(2, recording.getDate());;
        statementCall.setDouble(3, recording.getDuration_used());
        statementCall.setDouble(4, recording.getRec_offset());
        statementCall.execute();
    }

    public void createContributorRec(ContributorRec contributorRec) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_CONTRIBUTOR_REC(?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,contributorRec.getRec().getRecordingId());
        statementCall.setString(2, contributorRec.getCon().getContributorId());
        statementCall.setString(3, contributorRec.getRole().getRoleId());
        statementCall.execute();
    }
    public void createAlbum(Album album) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_ALBUM(?,?,?,?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,album.getAlbumid());
        statementCall.setString(2, album.getTitle());
        statementCall.setString(3, album.getCategory());
        statementCall.setDate(4,album.getPubdate());
        statementCall.setString(5, album.getMarket());
        statementCall.setString(6, album.getLabel());
        statementCall.execute();
    }

    public void createCollection(Collection collection) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_COLLECTION(?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1, collection.getCollectionId());
        statementCall.setString(2, collection.getName());
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

    public Connection getConnection(){
        return this.con;
    }
    public void closeConnection() throws SQLException{
        this.con.close();
    }
    //Date.valueOf("1997-03-10");
}
