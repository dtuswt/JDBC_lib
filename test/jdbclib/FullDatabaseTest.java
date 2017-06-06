package jdbclib;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by awo on 05/06/17.
 */
public class FullDatabaseTest {
    @Test
    public void testReadWrite() throws Exception {
        IConnector db = new DBConnector("h12-dev.wiberg.tech", 3306, "cdio_final", "hold12", "2017_h0lD!2");

        try {
            db.connectToDatabase();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        ResultSet s = db.query("CALL insertUser(\"John\", \"Doe\", \"JD\", \"SuperSecretPassword\", \"1\");");

        //System.out.println("ResultSet: " + s.toString());
//        System.out.println("ID: " + s.getString(1));

//        System.out.println("ID is " + s.getInt("user_id"));
//        assertNotNull(s.getInt("user_id"));
//        System.out.println("Generated user_id = " + s.getInt("user_id"));
//        assertEquals("John", s.getString("user_firstname"));
//        assertEquals("Doe", s.getString("user_lastname"));
//        assertEquals("JD", s.getString("initials"));
//        assertEquals("SuperSecretPassword", s.getString("password"));

        try {
            db.close();
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }
}