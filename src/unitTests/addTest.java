package src.unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Console;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import src.Credentials;
import src.MusicStudio;
import src.entities.Contributor;

public class addTest {
    Console console = System.console();
    // to run unit tests, insert pass your credentials into the variables below
    String user = "";
    String password = "";
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
