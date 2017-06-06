package jdbclib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by awo on 06/06/17.
 */
public class DatabaseConnection {
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

    public DatabaseConnection(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public DatabaseConnection() throws Exception {
        File file = new File(".env");
        Properties props = null;

        try (FileInputStream fileInputStream = new FileInputStream(file)){
            props = new Properties();
            props.load(fileInputStream);
        } catch (IOException e) {
            // File probably doesn't exist; ignore.
            return;
        }

        database = props.getProperty("DB_DATABASE");
        username = props.getProperty("DB_USERNAME");
        password = props.getProperty("DB_PASSWORD");
        host     = props.getProperty("DB_HOST"); try {
            port     = Integer.parseInt(props.getProperty("DB_PORT"));
        } catch (Exception e){ throw new Exception("DB_PORT not an integer."); }
    }

    public String getHost()     { return host;     }
    public int    getPort()     { return port;     }
    public String getDatabase() { return database; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
