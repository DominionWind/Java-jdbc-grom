package Lesson4.Lesson4NW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    private static final String DB_URL = "jdbc:oracle:thin:@dw-lessons.cdtoi0n98nro.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Utarasta287";

    FileDAO fileDAO = new FileDAO();
    StorageDAO storageDAO = new StorageDAO();

    public void put(Storage storage, File file) throws Exception {
        sizeValidator(storage, file);
        fileAvailability(storage, file);
        formatChecker(storage, file);
        file.setStorageId(storage.getId());
    }

    public void delete(Storage storage, File file) throws Exception {
        if (file.getStorageId() == storage.getId()) {
            file.setStorageId(0);
        }

        throw new Exception("file " + file.toString() + " not found in storage " + storage.toString());
    }

    public void transferAll(Storage storageFrom, Storage storageTo) {

    }


    private void sizeValidator(Storage storage, File file) throws Exception {
        ArrayList<File> files = new ArrayList<>();
        files.addAll(fileDAO.getAllFiles(storage.getId()));
        long sum = 0;
        for (File f : files) {
            sum += f.getSize();
        }

        if ((sum + file.getSize()) < Long.parseLong(storage.getStorageMaxSize())) {
            throw new Exception("Storage " + storage.toString() + " don`t have enought free spase");
        }
    }

    private void fileAvailability(Storage storage, File file) throws Exception {
        if (file.getStorageId() != 0) {
            throw new Exception("file " + file.toString() + " already in storage " + storage.toString());
        }
    }

    private void formatChecker(Storage storage, File file) throws Exception {
        int i = 0;
        for (String string : storage.getFormatsSupported()) {
            if (file.getFormat() == string) {
                i++;
            }
        }

        if (i == 0) {
            throw new Exception("Storage " + storage.toString() + " don`t support file format " + file.getFormat());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
