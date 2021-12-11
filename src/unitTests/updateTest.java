package src.unitTests;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import java.io.Console;
import java.sql.SQLException;

import org.junit.Test;

import src.Credentials;
import src.MusicStudio;
import src.entities.Contributor;
import src.entities.Recording;

public class updateTest {
    Console console = System.console();
    // to run unit tests, insert pass your credentials into the variables below
    String user = "";
    String password = "";
    Credentials credentials = new Credentials(user, password);
    @Test
    public void updateContributorNameTest() throws SQLException {
        MusicStudio m = new MusicStudio(credentials);
        Contributor c = new Contributor("C000991","Chrissy","Gracias");
        m.createContributor(c);
        m.updateTable("contributor", "c_first", c.getContributorId(), "LMAO");
        String query = "Select c_first from contributor where contributorid = ? ";
        PreparedStatement prep = m.getConnection().prepareStatement(query);
        prep.setString(1,c.getContributorId());
        ResultSet rs = prep.executeQuery();
        String name = "";
        if(rs.next()){
            name = rs.getString("c_first");
        }
        assertEquals("LMAO",name);
    }
    @Test
    public void updateRecordingOffsetTest() throws SQLException{
        MusicStudio m = new MusicStudio(credentials);
        Recording r = new Recording("RE090",Date.valueOf("2019-02-10"),10.5,1.0);
        m.createRecording(r);
        m.updateTable("recording", "offset", r.getRecordingId(),"2.2");
        String query = "Select offset from recording where recid = ? ";
        PreparedStatement prep = m.getConnection().prepareStatement(query);
        prep.setString(1,r.getRecordingId());
        ResultSet rs = prep.executeQuery();
        double o = 0;
        if(rs.next()){
            o = rs.getDouble("offset");
        }
        assertEquals(2.2,o);
        m.deleteRecording(r.getRecordingId());
    }
    @Test
    public void updateRecordingDateTest() throws SQLException{
        MusicStudio m = new MusicStudio(credentials);
        Recording r = new Recording("RE090",Date.valueOf("2019-02-10"),10.5,1.0);
        m.createRecording(r);
        m.updateTable("recording", "rec_date", r.getRecordingId(),"2019-02-11");
        String query = "Select rec_date from recording where recid = ? ";
        PreparedStatement prep = m.getConnection().prepareStatement(query);
        prep.setString(1,r.getRecordingId());
        ResultSet rs = prep.executeQuery();
        Date date = Date.valueOf("2020-01-01");
        if(rs.next()){
            date = rs.getDate("rec_date");
        }
        assertEquals(Date.valueOf("2019-02-11"),date);
        m.deleteRecording(r.getRecordingId());

    }
    @Test
    public void updatePrimaryKeyTest() throws SQLException{
        MusicStudio m = new MusicStudio(credentials);
        m.deleteContributor("C000991");
        Contributor c = new Contributor("C000991","Chrissy","Gracias");
        m.createContributor(c);
        try{
            m.updateTable("contributor", "contributorid", c.getContributorId(), "C000551");
            fail();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
