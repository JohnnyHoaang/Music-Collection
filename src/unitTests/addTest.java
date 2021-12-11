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
import src.entities.Recording;

public class addTest {
    Console console = System.console();
    // to run unit tests, insert pass your credentials into the variables below
    String user = "A2036759";
    String password = "Cow4life";
    Credentials credentials = new Credentials(user, password);

    @Test
    public void createContributorTest() throws SQLException {
        MusicStudio m = new MusicStudio(credentials);
        // if you want to run this unit test again, delete row by calling these methods
        // below
        // m.deleteContributor("C00001");
        Contributor c = new Contributor("C00001", "BOT1", "TEST");
        m.createContributor(c);
        String contributorName = "";
        String sql = "SELECT C_FIRST FROM CONTRIBUTOR WHERE CONTRIBUTORID = ?";
        PreparedStatement prep = m.getConnection().prepareStatement(sql);
        prep.setString(1, "C00001");
        ResultSet rs = prep.executeQuery();
        if (rs.next()) {
            contributorName = rs.getString("c_first");
        }
        assertEquals("BOT1", contributorName);
    }

    @Test
    public void createContributorIDPKErrorTest() throws SQLException {
        MusicStudio m = new MusicStudio(credentials);
        try {

            Contributor c = new Contributor("C00002", "BOT1", "TEST");
            m.createContributor(c);
            m.createContributor(c);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            m.deleteContributor("C00002");
        }
    }
    @Test
    public void createRecordingTest() throws SQLException {
        MusicStudio m = new MusicStudio(credentials);
        //m.deleteRecording("RE12345");
        Recording recording = new Recording("RE12345", Date.valueOf("2019-02-10"), 120, 10);
        m.createRecording(recording);
        Double recordingid = 0.0;
        String sql = "SELECT offset FROM recording WHERE recid = ?";
        PreparedStatement prep = m.getConnection().prepareStatement(sql);
        prep.setString(1, recording.getRecordingId());
        ResultSet rs = prep.executeQuery();
        if (rs.next()) {
            recordingid = rs.getDouble("offset");
        }
        assertEquals(10, recordingid);
    }
    @Test
    public void createRecordingPKErrorTest() throws SQLException {
        MusicStudio m = new MusicStudio(credentials);
        Recording recording = new Recording("RE12345", Date.valueOf("2019-02-10"), 120, 10);
        try {
            m.createRecording(recording);
            m.createRecording(recording);
            fail();
        } catch (Exception e) {
            m.deleteRecording(recording.getRecordingId());
        }
    }

    @Test
    public void createAlbumTest() throws SQLException {
        MusicStudio m = new MusicStudio(credentials);
        Album album = new Album("How to kill a bird","AL089","Ballad",Date.valueOf("2019-02-10"),"COL01", "World Wide","Ashley The Label");
        try {
            m.createAlbum(album);
            fail();
        } catch (Exception e) {
            m.deleteRecording(album.getCollectionid());
        }
    }

    @Test
    public void createCollectionTest() throws SQLException{
        MusicStudio m = new MusicStudio(credentials);
        Collection c = new Collection("COL03","Hehehe");
        try {
            m.createCollection(c);
            fail();
        } catch (Exception e) {
            m.deleteRecording(c.getCollectionId());
        }

    }

    @Test
    public void createContributorWithNonExistentRoleTest() throws SQLException {
        MusicStudio m = new MusicStudio(credentials);
        try {
            Contributor c = new Contributor("C00003", "BOT3", "TEST");
            m.createContributor(c);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
