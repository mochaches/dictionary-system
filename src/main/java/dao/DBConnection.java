package dao;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBConnection {
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
