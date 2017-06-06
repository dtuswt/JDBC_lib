package jdbclib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnector implements IConnector {
    private Connection connection;
    private Statement statement;
    private boolean connectionIsOpen = false; // TODO: use this??

    private String host, database, username, password;
    private int port;

    public DBConnector(String hostname, int port, String database, String username, String password) {
        this.host = "jdbc:mysql://"+hostname+":"+this.port+"/"+this.database;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public DBConnector() {
        this("127.0.0.1", 3306, "cdio_final", "root", "");
    }

    public DBConnector(DatabaseConnection databaseConnection) {
        this.host = "jdbc:mysql://"+databaseConnection.getHost()+":"+databaseConnection.getPort()+"/"+databaseConnection.getDatabase();
        this.port = databaseConnection.getPort();
        this.database = databaseConnection.getDatabase();
        this.username = databaseConnection.getUsername();
        this.password = databaseConnection.getPassword();
    }

    @Override
    public Connection connectToDatabase()
            throws ClassNotFoundException, SQLException, DALException {

        // See if JDBC Library is imported to the project
        if (!checkJDBCDriverExists())
            throw new ClassNotFoundException("JDBC library not found in the project.");

        // Establish a connection. If connection fails, an SQLException will be thrown
        this.connection = DriverManager.getConnection(this.host, this.username, this.password);

        return this.connection;
    }

    private boolean checkJDBCDriverExists() throws DALException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            return false;
        } catch (InstantiationException e) {
            throw new DALException("InstantiationException: " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new DALException("IllegalAccessException: " + e.getMessage());
        }
        return true;
    }

    @Override
    public ResultSet query(String cmd) throws DALException {
        if (connection == null) throw new DALException("Connection is closed (null).");

        // Create a statement
        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            throw new DALException("Failed to create a statement: " + e.getMessage());
        }

        // Try to return the ResultSet from the statement
        try {
            return this.statement.executeQuery(cmd);
        } catch (SQLException e) {
            throw new DALException("Failed to execute statement: " + e.getMessage());
        }
    }

    @Override
    public int update(String cmd) throws DALException {
        if (connection == null) throw new DALException("Connection is closed (null).");

        // Create a statement
        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            throw new DALException("Failed to create a statement: " + e.getMessage());
        }

        // Try to return the ResultSet from the statement
        try {
            return this.statement.executeUpdate(cmd);
        } catch (SQLException e) {
            throw new DALException("Failed to execute statement: " + e.getMessage());
        }
    }

    @Override
    public void close() throws DALException {
        try {
            if (this.statement != null) this.statement.close();
            if (this.connection != null) this.connection.close();
        } catch (SQLException e) {
            throw new DALException("Could not close connection: " + e.getMessage());
        }
    }
}
