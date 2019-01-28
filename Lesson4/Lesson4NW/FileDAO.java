package Lesson4.Lesson4NW;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@dw-lessons.cdtoi0n98nro.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    public File save(File file) throws SQLException {
        try (Connection connection = getConnection()) {
            saveFile(file, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return file;
    }

    public void delete(Long id) {
        try (Connection connection = getConnection()) {
            deleteFile(id, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public File update(File file) {
        try (Connection connection = getConnection()) {
            updateFile(file, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return file;
    }

    public File findById(Long id) {
        try (Connection connection = getConnection()) {
            findByIdFile(id, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<File> getAllFiles(long id) {
        try (Connection connection = getConnection()) {
            getAllFilesFromStorage(id, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAllFiles(long id) {
        try (Connection connection = getConnection()) {
            deleteAllFilesFromStorage(id, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private void deleteAllFilesFromStorage(Long id, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("DELETE * FROM FILE WHERE STORAGE_ID = ?")) {
            connection.setAutoCommit(false);
            prepareStatement.setLong(1, id);
            prepareStatement.executeQuery();
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Can`t find Storage with id " + id);
            connection.rollback();
            throw e;
        }
    }

    private List<File> getAllFilesFromStorage(Long id, Connection connection) throws SQLException {
        ArrayList<File> files = new ArrayList<>();
        try (PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM FILE WHERE STORAGE_ID = ?")) {
            connection.setAutoCommit(false);
            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                files.add(setFileConvector(resultSet));
            }
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Can`t find Storage with id " + id);
            connection.rollback();
            throw e;
        }
        return files;
    }

    private File findByIdFile(Long id, Connection connection) throws SQLException {
        File file = null;
        try (PreparedStatement prepareStatement = connection.prepareStatement("SELECT FROM FILE WHERE ID = ?")) {
            connection.setAutoCommit(false);
            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                file = setFileConvector(resultSet);
            }
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Can`t find file with id " + id);
            connection.rollback();
            throw e;
        }
        return file;
    }


    private void updateFile(File file, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("UPDATE FILE SET VALUES (?, ?, ?, ?, ?) " +
                "WHERE ID = ?")) {

            connection.setAutoCommit(false);
            fileConvector(prepareStatement, file);
            prepareStatement.setLong(6, file.getId());
            prepareStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Can`t update file " + file.toString());
            connection.rollback();
            throw e;
        }
    }


    private void deleteFile(Long id, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM FILE WHERE ID = ?)")) {

            connection.setAutoCommit(false);
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Can`t delete file " + id);
            connection.rollback();
            throw e;
        }
    }

    private void saveFile(File file, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO FILE VALUES (?, ?, ?, ?, ?)")) {

            connection.setAutoCommit(false);
            fileConvector(prepareStatement, file);
            prepareStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            connection.rollback();
            throw e;
        }
    }

    private File setFileConvector(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String format = resultSet.getString(3);
        long size = resultSet.getLong(4);
        long storageId = resultSet.getLong(5);
        return new File(id, name, format, size, storageId);
    }

    private void fileConvector(PreparedStatement preparedStatement, File file) throws SQLException {
        try {
            preparedStatement.setLong(1, file.getId());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getFormat());
            preparedStatement.setLong(4, file.getSize());
            preparedStatement.setLong(5, file.getStorageId());
        } catch (SQLException e) {
            System.err.println("Can`t convert file " + file.toString());
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
