package org.example.repository;

import org.example.entity.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;

public class UserRepo {
    private Connection connection;
    private final String TABLE_NAME = "my_test_database.user";


    public UserRepo(Connection connection) {
        this.connection = connection;
    }


    public void insertUser(User user) throws SQLException {
        String query = "insert into  " + TABLE_NAME + "(username,password) " +
                "values(?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, hashPassword(user.getPassword()));
        preparedStatement.executeUpdate();
    }



    public boolean checkUser(String username, String password) throws SQLException {
        boolean userExists = false;


        String query = "SELECT username, password FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.getString("username").equals(username)
                        && resultSet.getString("password").equals(password)) {
                    userExists = true;
                }
            }
        }
        return userExists;
    }

    private String hashPassword(String password) throws RuntimeException {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);

        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return toHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    private String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
