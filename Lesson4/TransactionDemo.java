package Lesson4;

import Lesson3.task1.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDemo {

    private static final String DB_URL = "jdbc:oracle:thin:@dw-lessons.cdtoi0n98nro.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    public static void main(String[] args) throws SQLException {

        Product product1 = new Product(55, "!!!", "!!!", 7777);
        Product product2 = new Product(66, "!!!", "!!!", 7777);
        Product product3 = new Product(77, "!!!", "!!!", 7777);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        save(products);
    }

    public static void save(List<Product> products) throws SQLException {
        try (Connection connection = getConnection()) {
            saveList(products, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void saveList(List<Product> products, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO PRODUCT VALUES (?, ?, ?, ?)")) {

            connection.setAutoCommit(false);

            for (Product product : products) {
                prepareStatement.setLong(1, product.getId());
                prepareStatement.setString(2, product.getName());
                prepareStatement.setString(3, product.getDescription());
                prepareStatement.setInt(4, product.getPrice());

                int res = prepareStatement.executeUpdate();

                System.out.println("save was finished with result " + res);
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            connection.rollback();
            throw e;
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
