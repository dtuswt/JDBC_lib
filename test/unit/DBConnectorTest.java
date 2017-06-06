package unit;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import jdbclib.*;

/**
 * Created by awo on 05/06/17.
 */

// I know this is technically not a unit test...
public class DBConnectorTest {
    private DatabaseConnection databaseConnection;

    @Before
    public void setup() throws Exception {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (Exception e) {
            return; /* No .env file exists. */
        }
    }

    @Test
    public void TestConnectDatabase() throws Exception {
        IConnector db;
        if (databaseConnection != null) db = new DBConnector(databaseConnection);
        else db = new DBConnector();

        try {
            db.connectToDatabase();
            db.close();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("JDBC library not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Problems while connecting to database.");
        } catch (DALException e) {
            throw new DALException("Failed to close connection.");
        }

        return; // Pass the test
    }
}