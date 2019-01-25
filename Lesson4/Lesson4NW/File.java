package Lesson4.Lesson4NW;

public class File {
    private Long id;
    private String name;
    private String format;
    private Long size;
    private long storageId;

    public File(Long id, String name, String format, Long size, long storageId) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.size = size;
        this.storageId = storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public Long getSize() {
        return size;
    }

    public long getStorageId() {
        return storageId;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storageId=" + storageId +
                '}';
    }
}
