package jdbclib;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by awo on 05/06/17.
 */
public class FullDatabaseTest {
    @Test
    public void testReadWrite() throws Exception {
        IConnector db = new DBConnector();

        try {
            db.connectToDatabase();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        db.update("CALL insertUser(\"John\", \"Doe\", \"JD\", \"SuperSecretPassword\");");

        try {
            db.close();
        } catch (DALException e) {
            throw new DALException(e.getMessage());
        }
    }
}