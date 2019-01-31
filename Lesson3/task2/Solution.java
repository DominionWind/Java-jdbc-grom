package Lesson3.task2;

import java.sql.*;
import java.util.Date;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@dw-lessons.cdtoi0n98nro.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public long testSavePerformance() { // testSavePerformance - 126389 testSavePerformance - 125156
        long startDate = new Date().getTime();
        long endDate;
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO TEST_SPEED VALUES (?, ?, ?)")) {
            int i = 1000;
            while (i != 0) {
                prepareStatement.setLong(1, i);
                prepareStatement.setString(2, "test");
                prepareStatement.setInt(3, i);
                int res = prepareStatement.executeUpdate();
                i--;
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        endDate = new Date().getTime();
        System.out.println("testSavePerformance - " + (endDate - startDate));
        return endDate - startDate;
    }

    public long testDeleteByIdPerformance() { //testDeleteByIdPerformance - 124949
        long startDate = new Date().getTime();
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM TEST_SPEED WHERE ID = ?")) {
            int i = 1000;
            while (i != 0) {
                prepareStatement.setLong(1, i);
                ;
                int res = prepareStatement.executeUpdate();
                i--;
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        long endDate = new Date().getTime();
        System.out.println("testDeleteByIdPerformance - " + (endDate - startDate));
        return endDate - startDate;
    }

    public long testDeletePerformance() { //testDeletePerformance - 1468
        long startDate = new Date().getTime();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery("DELETE FROM TEST_SPEED");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        long endDate = new Date().getTime();
        System.out.println("testDeletePerformance - " + (endDate - startDate));
        return endDate - startDate;
    }

    public long testSelectByIdPerformance() { //testSelectByIdPerformance - 135961
        long startDate = new Date().getTime();
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM TEST_SPEED WHERE ID = ?")) {
            int i = 1000;
            while (i != 0) {
                prepareStatement.setLong(1, i);
                ;
                int res = prepareStatement.executeUpdate();
                i--;
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        long endDate = new Date().getTime();
        System.out.println("testSelectByIdPerformance - " + (endDate - startDate));
        return endDate - startDate;
    }

    public long testSelectPerformance() { //testSelectPerformance - 1906
        long startDate = new Date().getTime();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery("SELECT * FROM TEST_SPEED");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }

        long endDate = new Date().getTime();
        System.out.println("testSelectPerformance - " + (endDate - startDate));
        return endDate - startDate;
    }
}
