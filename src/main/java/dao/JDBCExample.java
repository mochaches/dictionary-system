package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:postgresql://localhost:8080/postgres";
    static final String JDBC_DRIVER = "org.postgresql.Driver";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "admin";

    public static void main(String[] args) {
//        Properties properties = new Properties();
        try (//FileInputStream file = new FileInputStream("src/main/resources/config.properties");
             Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()
        ) {
            //STEP 2: Register JDBC driver
//            Class.forName(properties.getProperty("db.jdbcDriver"));
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");

            //STEP 4: Execute a query
            System.out.println("Creating database...");

            String sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample