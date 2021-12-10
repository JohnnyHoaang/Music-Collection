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
        // m.deleteContributor(c.getContributorId());
        // m.deleteRecording("RE0000");
        //m.createContributor(c, "R001", "RE0000");
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
        m.deleteRecording("RE0000");
    }
    @Test 
    public void deleteSongTest(){

    }
    @Test
    public void deleteCollectionTest(){

    }
    @Test
    public void deleteRecordingTest() throws SQLException{
        MusicStudio m = new MusicStudio(credentials);
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