package jdbclib;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by awo on 05/06/17.
 */
public class FullDatabaseTest {
    DatabaseConnection databaseConnection;

    @Before
    public void setup() {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (Exception e) {
            return; /* No .env file. */
        }
    }

    @Test
    public void testReadWrite() throws Exception {
        IConnector db;

        if (databaseConnection!= null) db = new DBConnector(databaseConnection);
        else db = new DBConnector();

        try {
            db.connectToDatabase();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }

        ResultSet s = db.query("CALL insertUser(\"John\", \"Doe\", \"JD\", \"SuperSecretPassword\", \"1\");");

        assertTrue(s.first());
        assertNotNull(s.getInt(1));


//        assertEquals("John", newUser.getString(2));

        try {
            db.close();
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }

//        try {
//            db.connectToDatabase();
//        } catch (ClassNotFoundException e) {
//            throw new ClassNotFoundException(e.getMessage());
//        } catch (SQLException e) {
//            throw new SQLException(e.getMessage());
//        }
//
//        ResultSet newUser = db.query("SELECT * FROM adm_user WHERE id = " + userid + ";");
    }
}