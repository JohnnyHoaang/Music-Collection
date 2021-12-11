package src.unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Console;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import src.Credentials;
import src.MusicStudio;
import src.entities.Album;
import src.entities.Collection;
import src.entities.Contributor;


public class deleteTest {
    Console console = System.console();
    // to run unit tests, insert pass your credentials into the variables below
    String user = "";
    String password = "";
    Credentials credentials = new Credentials(user, password);
    
    @Test
    public void deleteContributorTest() throws SQLException{
        MusicStudio m = new MusicStudio(credentials);
        Contributor c = new Contributor("C000011", "BOBBY", "JUNIOR");
        //uncomment if you want to run test again
        //m.deleteContributor(c.getContributorId());
        m.createContributor(c);
        m.deleteContributor(c.getContributorId());
        String contributorName ="";
        String sql = "SELECT C_FIRST FROM CONTRIBUTOR WHERE CONTRIBUTORID = ?";
        PreparedStatement prep = m.getConnection().prepareStatement(sql);
        prep.setString(1, "C000011");
        ResultSet rs = prep.executeQuery();
        if(rs.next()){
            contributorName = rs.getString(1);
        }
        assertEquals("", contributorName);
    }
    @Test 
    public void deleteSongTest() throws SQLException{
        MusicStudio m = new MusicStudio(credentials);
        Album album = new Album("AL00021", "WOW", "CINEMA", Date.valueOf("2021-12-10"), "", "MONEY", "STARS");
        //uncomment if you want to run test again
        //m.deleteSong(album.getAlbumid());
        m.createAlbum(album);
        m.deleteSong(album.getAlbumid());
        String title ="";
        String sql = "SELECT title FROM ALBUM WHERE albumid = ?";
        PreparedStatement prep = m.getConnection().prepareStatement(sql);
        prep.setString(1, album.getAlbumid());
        ResultSet rs = prep.executeQuery();
        if(rs.next()){
            title = rs.getString(1);
        }
        assertEquals("", title);
    }
    @Test
    public void deleteCollectionTest() throws SQLException{
        MusicStudio m = new MusicStudio(credentials);
        Collection collection = new Collection("COL042", "DELTE");
        //uncomment if you want to run test again
        //m.deleteCollection(collection.getCollectionId());
        m.createCollection(collection);
        m.deleteCollection(collection.getCollectionId());
        String name ="";
        String sql = "SELECT name FROM collection WHERE collectionid = ?";
        PreparedStatement prep = m.getConnection().prepareStatement(sql);
        prep.setString(1, collection.getCollectionId());
        ResultSet rs = prep.executeQuery();
        if(rs.next()){
            name = rs.getString(1);
        }
        assertEquals("", name);
    }
    @Test
    public void deleteRecordingTest() throws SQLException{
        MusicStudio m = new MusicStudio(credentials);
        //uncomment if you want to run test again
        //m.deleteRecording("RE0123");
        String vrecid ="";
        String sqlUpdate = "INSERT INTO RECORDING VALUES(?,?,?,?)";
        PreparedStatement prepUpdate = m.getConnection().prepareStatement(sqlUpdate);
        prepUpdate.setString(1, "RE0123");
        prepUpdate.setDate(2, Date.valueOf("2015-03-31"));
        prepUpdate.setDouble(3, 1.4);
        prepUpdate.setDouble(4, 0.07);
        prepUpdate.executeUpdate();
        m.deleteRecording("RE0123");
        String sqlretrieve = "SELECT recid FROM recording WHERE recid = ?";
        PreparedStatement prepRetrieve = m.getConnection().prepareStatement(sqlretrieve);
        prepRetrieve.setString(1, "RE0123");
        ResultSet rs = prepRetrieve.executeQuery();
        if(rs.next()){
            vrecid = rs.getString(1);
        }
        assertEquals("", vrecid);
    }
}