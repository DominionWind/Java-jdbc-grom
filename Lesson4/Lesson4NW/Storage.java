package Lesson4.Lesson4NW;

import java.util.Arrays;

public class Storage {
    private long id;
    private String[] formatsSupported;
    private String storageCountry;
    private String storageMaxSize;

    public Storage(long id, String[] formatsSupported, String storageCountry, String storageMaxSize) {
        this.id = id;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageMaxSize = storageMaxSize;
    }

    public long getId() {
        return id;
    }

    public String[] getFormatsSupported() {
        return formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public String getStorageMaxSize() {
        return storageMaxSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported=" + Arrays.toString(formatsSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageMaxSize='" + storageMaxSize + '\'' +
                '}';
    }
}
