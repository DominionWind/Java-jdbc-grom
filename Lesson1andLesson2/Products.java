package Lesson1andLesson2;

public class Products {
    private long id;
    private String name;
    private String Description;
    private int price;

    public Products(long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        Description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Lesson1andLesson2.Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Description='" + Description + '\'' +
                ", price=" + price +
                '}';
    }
}
