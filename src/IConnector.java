import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConnector {
    Connection connectToDatabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
    ResultSet query(String cmd) throws DALException;
    int update(String cmd) throws DALException;
    void close() throws DALException;
}