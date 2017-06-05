package unit;

import org.junit.Test;
import java.sql.SQLException;
import jdbclib.*;
import static org.junit.Assert.*;

/**
 * Created by awo on 05/06/17.
 */

// I know this is technically not a unit test...
public class DBConnectorTest {
    @Test
    public void TestConnectDatabase() throws Exception {
        IConnector db = new DBConnector("127.0.0.1", 3306, "dbweight", "root", "");

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