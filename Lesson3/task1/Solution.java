package Lesson3.task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static final String DB_URL = "jdbc:oracle:thin:@dw-lessons.cdtoi0n98nro.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    public List<Product> findProductByPrice(int price, int delta) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?")) {
            prepareStatement.setInt(1, price - delta);
            prepareStatement.setInt(2, price + delta);

            List<Product> products = new ArrayList<>();

            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                products.add(productConvector(resultSet));
            }
            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> findProductsByName(String word) throws Exception {

        if (word.length() < 3)
            throw new Exception("wold " + word + " length is too short");

        for (char c : word.toCharArray()) {
            if (!(Character.isDigit(c) || Character.isLetter(c))) {
                throw new Exception("Not correct word" + word);
            }
        }

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE NAME LIKE ?")) {
            prepareStatement.setString(1, "%" + word + "%");

            List<Product> products = new ArrayList<>();

            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                products.add(productConvector(resultSet));
            }
            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> findProductWithEmptyDescription() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL");

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(productConvector(resultSet));
            }
            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    private Product productConvector(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);
        int p = resultSet.getInt(4);
        Product product = new Product(id, name, description, p);
        return product;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
