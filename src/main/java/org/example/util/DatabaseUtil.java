package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseUtil {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/my_test_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";


    public DatabaseUtil() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found", e);
        }


        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }

    public Connection getConnection() {
        return connection;
    }
}
