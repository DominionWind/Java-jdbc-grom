package Lesson1andLesson2;

import java.sql.*;
import java.util.ArrayList;

public class Solution {

    private static final String DB_URL = "jdbc:oracle:thin:@dw-lessons.cdtoi0n98nro.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    public ArrayList<Products> getAllProducts() {

        ArrayList<Products> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS");
            {
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String Description = resultSet.getString(3);
                    int price = resultSet.getInt(4);
                    Products product = new Products(id, name, Description, price);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return products;
    }

    public ArrayList<Products> getProductsByPrice() {

        ArrayList<Products> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS WHERE PRICE <= 100");
            {
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String Description = resultSet.getString(3);
                    int price = resultSet.getInt(4);
                    Products product = new Products(id, name, Description, price);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return products;
    }

    public ArrayList<Products> getProductsByDescription() {

        ArrayList<Products> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS WHERE PRICE <= 100");
            {
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String Description = resultSet.getString(3);
                    int price = resultSet.getInt(4);
                    Products product = new Products(id, name, Description, price);

                    if (product.getDescription().length() > 50) {
                        products.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return products;
    }

    public void IncreasedPrise() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("UPDATE * FROM PRODUCTS " +
                    "SET PRICE = PRICE + 100" +
                    "WHERE PRICE <= 970");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void changeDescription () {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("DELETE FROM PRODUCT " +
                    "WHERE ID = (SELECT ID FROM PRODUCT ORDER BY ID DESC LIMIT 1) " +
                    "WHERE LENGTH DESCRIPTION > 100");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
