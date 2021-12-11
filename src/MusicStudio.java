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
    /**
     * returns the credentials object of user
     */
    public Credentials getCreds() {
        return creds;
    }
    /**
     * 
     * @param username
     * @param password
     * @return returns the connection from DB
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public Connection connectToDB(String username, String password) throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca",
                username, password );
    }
    /**
     * 
     * @return logs object filled with all the logs of current user
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public Logs getUserLogs() throws SQLException{
        Logs logMessage = new Logs(this.creds.getUser());
        
        String retrieveLogs = "SELECT * FROM USER_LOGS WHERE USERNAME = ?";
        PreparedStatement prep = this.con.prepareStatement(retrieveLogs);
        prep.setString(1, this.creds.getUser());

        ResultSet rs = prep.executeQuery();

        while(rs.next()){
            logMessage.addTrack(rs.getString("changes"));
        }
        return logMessage;
    }
    /**
     * 
     * @param albumid
     * @return returns an album object filled with data from albumid
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
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
    /**
     * 
     * @param collectionid
     * @return returns a collection object filled with data from collectionid
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
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
    /**
     * 
     * @param contributorid
     * @return returns a contributor object filled with data from contributorid
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
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
    /**
     * 
     * @param roleid
     * @return returns a role object filled with data from roleid
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
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
    /**
     * 
     * @param recordingid
     * @return returns an recording object filled with data from recording id
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public Recording getRecording(String recordingid) throws SQLException{
        Recording recording = null;
        String contributor = "SELECT * FROM RECORDING WHERE recid = ?";
        PreparedStatement prep = this.con.prepareStatement(contributor);
        prep.setString(1, recordingid);
        ResultSet rs = prep.executeQuery();
        while(rs.next()){
            recording = new Recording(rs.getString("recid"), rs.getDate("rec_date"), rs.getDouble("duration"), rs.getDouble("offset"));
        }
        return recording;
    }


    /**
     * 
     * returns the contributors and roles based on the albumid
     * 
     * @param albumid
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void rolesFromContributors(String albumid) throws SQLException{

        String query = "SELECT UNIQUE contributorid, roleid, rolename from compilation JOIN RECORDING USING(recid) JOIN CONTRIBUTOR_REC USING(recid)"+
                            "JOIN CONTRIBUTOR_ROLE using(roleid) WHERE albumid = ?";
        PreparedStatement prep = this.con.prepareStatement(query);
        prep.setString(1, albumid);
        
        ResultSet rs = prep.executeQuery();

        while(rs.next()){
            Contributor contributor = this.getContributor(rs.getString(1));
            Role role = new Role(rs.getString(2),rs.getString(3));

            System.out.println(contributor + " ... " + role);
        }
    }

    /**
     * 
     * @param albumid
     * @return returns a list of recordings from albumid
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public ArrayList<Recording> recordingsFromAlbum(String albumid) throws SQLException{
        String query = "SELECT UNIQUE recid,rec_date,duration,offset from compilation JOIN RECORDING USING(recid) WHERE albumid = ?";
        
        ArrayList<Recording> recordings = new ArrayList<>();            
        PreparedStatement prep = this.con.prepareStatement(query);
        prep.setString(1,albumid);

        ResultSet rs = prep.executeQuery();

        while(rs.next()){
            recordings.add(new Recording(rs.getString(1),rs.getDate(2),rs.getDouble(3),rs.getDouble(4)));
        }

        return recordings;
    }
    /**
     * 
     * @param albumid
     * @return returns collection from an albumid
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public Collection collectionFromAlbum(String albumid) throws SQLException{
        Collection collection = null;

        String query = "SELECT collectionid,name FROM COLLECTION JOIN ALBUM using(collectionid) WHERE albumid = ?";
        
        PreparedStatement prep = this.con.prepareStatement(query);
        prep.setString(1,albumid);

        ResultSet rs = prep.executeQuery();

        while(rs.next()){
            collection = new Collection(rs.getString(1),rs.getString(2));
        }

        return collection;
    }
    /**
     * 
     * @param collectionid
     * @return returns all albums within a collection
     * @throws SQLException 
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public ArrayList<Album> albumsInCollection(String collectionid) throws SQLException{
        String query = "SELECT albumid,title,category,pubdate,collectionid,market,label "+
                            "FROM ALBUM JOIN COLLECTION using(collectionid) WHERE collectionid = ?";
        
        ArrayList<Album> albums = new ArrayList<>();  
        PreparedStatement prep = this.con.prepareStatement(query);
        prep.setString(1,collectionid);

        ResultSet rs = prep.executeQuery();

        while(rs.next()){
            albums.add(new Album(rs.getString(1), rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7)));
        }

        return albums;
        
    }

    /**
     * prints all field of a table
     * @param table
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void printAllFieldsFromTable(String table) throws SQLException{
        switch(table){
            case "album":
            System.out.println("title, category, pubdate, collectionid, market, label");
            break;
            case "collection":
            System.out.println("name");
            break;
            case "recording" :
            System.out.println("date, duration, offset");
            break;
            case "contributor":
            System.out.println("c_first, c_last");
            break;
            case "contributor_role":
            System.out.println("rolename");
            break;
        }
    }
    /**
     * prints all id rows given a table name
     * @param table
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void printAllIDRowsFromTable(String table) throws SQLException{
        String id = "";
        String name = "";
        //Refactor into method
        switch(table){
            case "album":
            id = "albumid";
            name = "title,";
            break;
            case "collection":
            id = "collectionid";
            name = "name,";
            break;
            case "recording":
            id = "recid";
            break;
            case "contributor":
            id = "contributorid";
            name = "(c_first ||' '|| c_last),";
            break;
            case "contributor_role":
            id = "roleid";
            name = "rolename,";
            break;
        }

        String printID = "SELECT "+name+id+" FROM "+table;
        PreparedStatement prep = this.con.prepareStatement(printID);
        ResultSet rs = prep.executeQuery();

        if(table.equals("recording")){
            while(rs.next()){
                System.out.println("ID:" + rs.getString(1));
            }
        } else {

        while(rs.next()){
            System.out.println("Name: "+rs.getString(1) + ", ID:" + rs.getString(2));
        }
     }

    }
    /**
     * prints all roles
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
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

    /**
     * Storing the recordings into an object
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void printAllRecording() throws SQLException{
        String recording = "SELECT * FROM RECORDING";
        PreparedStatement prep = this.con.prepareStatement(recording);
        ResultSet rs = prep.executeQuery();

        ArrayList<Recording> recordings = new ArrayList<>();

        while(rs.next()){
            
            recordings.add(new Recording(rs.getString("recid"), rs.getDate("rec_date"), rs.getDouble("duration"), rs.getDouble("offset")));
        }

        for(Recording rec : recordings){
            System.out.println(rec);
            System.out.println("---------------------------");
        }
    }

    /**
     * Storing the collections into an object
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
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

    /**
     * Storing the songs into an object
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
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
    /**
     * prints all existing tables
     */
    public void printAllTables(){
        System.out.println("\n" + "Here are the tables:");
        System.out.println("contributor, recording, album, collection");
        System.out.println("contributor_role, contributor_rec, compilation"); 
        System.out.println();
    }

    //adds data by calling the procedures from the package addpkg
    /**
     * creates contributor given a contributor object
     * @param contributor
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void createContributor(Contributor contributor) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_CONTRIBUTOR(?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,contributor.getCfirst());
        statementCall.setString(2,contributor.getClast());
        statementCall.setString(3, contributor.getContributorId());
        statementCall.execute();
    }
    /**
     * creates a recording given a recording object
     * @param recording
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void createRecording(Recording recording) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_RECORDING(?,?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,recording.getRecordingId());
        statementCall.setDate(2, recording.getDate());;
        statementCall.setDouble(3, recording.getDuration_used());
        statementCall.setDouble(4, recording.getRec_offset());
        statementCall.execute();
    }
    /**
     * creates a contributor rec given a contributor rec object
     * @param contributorRec
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void createContributorRec(ContributorRec contributorRec) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_CONTRIBUTOR_REC(?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,contributorRec.getRec().getRecordingId());
        statementCall.setString(2, contributorRec.getCon().getContributorId());
        statementCall.setString(3, contributorRec.getRole().getRoleId());
        statementCall.execute();
    }
    /**
     * creates album given album object
     * @param album
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
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
    /**
     * creates collection given collection id
     * @param collection
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void createCollection(Collection collection) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_COLLECTION(?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1, collection.getCollectionId());
        statementCall.setString(2, collection.getName());
        statementCall.execute();
    }
    /**
     * creates compilation given a compilation id
     * @param recording
     * @param album
     * @param vdate
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void createCompilation(Recording recording, Album album, Date vdate) throws SQLException{
        String callProcedure = "{call addpkg.CREATE_COMPILATION(?,?,?)}";
        CallableStatement statementCall = this.con.prepareCall(callProcedure);
        statementCall.setString(1,recording.getRecordingId());
        statementCall.setDate(2, vdate);
        statementCall.setString(3, album.getAlbumid());
        statementCall.execute();
    }

    //Delete data from the table by calling procedures from the package deletepkg
    /**
     * deletes song given a given albumid
     * @param albumId
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void deleteSong(String albumId) throws SQLException{
        String delSong = "{call deletepkg.DELETE_SONG(?)}";
        CallableStatement statementCall = this.con.prepareCall(delSong);
        statementCall.setString(1, albumId);
        statementCall.execute();
    }
    /**
     * deletes a contributor given a contributor id
     * @param contributorId
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void deleteContributor(String contributorId) throws SQLException{
        String delContr = "{call deletepkg.DELETE_CONTRIBUTOR(?)}";
        CallableStatement statementCall = this.con.prepareCall(delContr);
        statementCall.setString(1, contributorId);
        statementCall.execute();
    }
    /**
     * deletes collection given a collection id
     * @param collectionId
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void deleteCollection(String collectionId) throws SQLException{
        String delColl = "{call deletepkg.DELETE_COLLECTION(?)}";
        CallableStatement statementCall = this.con.prepareCall(delColl);
        statementCall.setString(1, collectionId);
        statementCall.execute();
    }
    /**
     * deletes recording given a recording id
     * @param recordingid
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public void deleteRecording(String recordingid) throws SQLException{
        String delColl = "{call deletepkg.DELETE_RECORDING(?)}";
        CallableStatement statementCall = this.con.prepareCall(delColl);
        statementCall.setString(1, recordingid);
        statementCall.execute();
    }
    /**
     * updates table depending on data given
     * @param table
     * @param column
     * @param givenId
     * @param newData
     * @throws SQLException
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
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
        if(column.equals("recid")||column.equals("collectionid")||column.equals("contributorid")||column.equals("roleid")||column.equals("albumid")){
            throw new IllegalArgumentException("you cannot change primary keys!");

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
    /**
     * 
     * @return returns current connection 
     * @author Domenico Cuscuna, Johnny Hoang, Ashley Vu
     */
    public Connection getConnection(){
        return this.con;
    }
    /**
     * closes current connection
     * @throws SQLException
     */
    public void closeConnection() throws SQLException{
        this.con.close();
    }
}
