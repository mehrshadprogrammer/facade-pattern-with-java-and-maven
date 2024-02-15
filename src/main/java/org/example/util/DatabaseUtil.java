package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    private Connection connection;
    private String url;
    private String username;
    private String password;


    public DatabaseUtil() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        this.url = "jdbc:mysql://localhost:3306/my_test_database";
        this.username = "root";
        this.password = "123456";

        Properties properties = new Properties();
        properties.put("user", username);
        properties.put("password", password);
        this.connection = DriverManager.getConnection(url, properties);

    }

    public Connection getConnection() {
        return connection;
    }

}
