package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final Logger log = LoggerFactory.getLogger(DBConnection.class);
    private Connection conn = null;
    static final String DB_URL = "jdbc:postgresql://localhost:8080/postgres";
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String USER = "postgres";
    static final String PASS = "admin";

    public void close() {
        if (conn != null) {
            try {
                log.info("Closing database connection to sampleDB");
                conn.close();
            } catch (SQLException e) {
                log.error("Unable to close connection", e);
            }
            conn = null;
        }
    }

    public Connection getConnection() throws SQLException {
        if (conn == null) {
            log.info("Opening connection to sampleDB");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return conn;
    }
}
