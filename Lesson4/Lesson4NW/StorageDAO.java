package Lesson4.Lesson4NW;

import java.sql.*;
import java.util.Arrays;

public class StorageDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@dw-lessons.cdtoi0n98nro.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    public Storage save(Storage storage) throws SQLException {
        try (Connection connection = getConnection()) {
            saveStorage(storage, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return storage;
    }

    public void delete(long id){
        try (Connection connection = getConnection()) {
            deleteStorage(id, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public Storage update(Storage storage) {
        try (Connection connection = getConnection()) {
            updateStorage(storage, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return storage;
    }

    public Storage findById(Long id) {
        try (Connection connection = getConnection()) {
            findByIdStorage(id, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    private Storage findByIdStorage(Long id, Connection connection) throws SQLException {
        Storage storage = null;
        try (PreparedStatement prepareStatement = connection.prepareStatement("SELECT FROM FILE WHERE ID = ?")) {
            connection.setAutoCommit(false);
            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                storage = setStorageConvector(resultSet);
            }
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Can`t find storage with id " + id);
            connection.rollback();
            throw e;
        }
        return storage;
    }

    private void updateStorage(Storage storage, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("UPDATE STORAGE SET VALUES (?, ?, ?, ?) " +
                "WHERE ID = ?")) {

            connection.setAutoCommit(false);
            storageConvector(prepareStatement, storage);
            prepareStatement.setLong(5, storage.getId());
            prepareStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Can`t update storage " + storage.toString());
            connection.rollback();
            throw e;
        }
    }

    private void deleteStorage(Long id, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM STORAGE WHERE ID = ?)")) {

            connection.setAutoCommit(false);
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Can`t delete storage with " + id);
            connection.rollback();
            throw e;
        }
    }

    private void saveStorage(Storage storage, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO STORAGE VALUES (?, ?, ?, ?)")) {

            connection.setAutoCommit(false);
            storageConvector(prepareStatement, storage);
            prepareStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            connection.rollback();
            throw e;
        }
    }

    private Storage setStorageConvector(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String[] formatsSupported = resultSet.getString(2).split(",");
        String storageCountry = resultSet.getString(3);
        String storageMaxSize = resultSet.getString(4);
        return new Storage(id, formatsSupported, storageCountry, storageMaxSize);
    }

    private void storageConvector(PreparedStatement preparedStatement, Storage storage) throws SQLException {
        try {
            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, Arrays.toString(storage.getFormatsSupported()));
            preparedStatement.setString(3, storage.getStorageCountry());
            preparedStatement.setString(4, storage.getStorageMaxSize());
        } catch (SQLException e) {
            System.err.println("Can`t convert storage " + storage.toString());
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
