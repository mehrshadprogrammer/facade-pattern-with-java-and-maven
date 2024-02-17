package org.example.repository;

import org.example.entity.Product;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private Connection connection;

    private final String TABLE_NAME = "my_test_database.product";

    public ProductRepo(Connection connection) {
        this.connection = connection;
    }

    public List<Product> showAllProducts() throws SQLException{
        List<Product> products = new ArrayList<>();
        String query = "select * from " + TABLE_NAME;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int count = resultSet.getInt("count");
            products.add(new Product(name, price, count));
            System.out.println("==================");
            System.out.println(resultSet.getString("name"));
            System.out.println("price: "+resultSet.getDouble("price"));
            System.out.println("count: "+resultSet.getInt("count"));
        }
        return products;
    }

    public void insertProduct(Product product) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (name, price, count) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getCount());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Exception oh!!!");
            }
        }
    }
}
