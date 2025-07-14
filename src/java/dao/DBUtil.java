// This class handles the database connection.
// - Include method to connect to PostgreSQL using JDBC
// - Load DB driver, manage credentials (can use constants or a .properties file)
// - Provide static getConnection() method for DAO classes

// Barend here -> added this for my side so it does not have errors

//package dao;
//import com.sun.jdi.connect.spi.Connection;
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBUtil {
    private static final Properties props = new Properties();

    static {
             try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            props.load(input);
            
            } catch (IOException e) {
            throw new ExceptionInInitializerError("Could not load database properties");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            props.getProperty("db.url"),
            props.getProperty("db.user"),
            props.getProperty("db.password")
        );
    }
}

