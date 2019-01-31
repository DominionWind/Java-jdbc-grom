package Lesson1andLesson2;

import java.util.Date;

public class Orders {
    private long id;
    private String productName;
    private int price;
    private Date dateOrdered;
    private Date dateConfirmed;

    public Orders(long id, String productName, int price, Date dateOrdered, Date dateConfirmed) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.dateOrdered = dateOrdered;
        this.dateConfirmed = dateConfirmed;
    }

    @Override
    public String toString() {
        return "src.main.java.Lesson1andLesson2.Orders{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", dateOrdered=" + dateOrdered +
                ", dateConfirmed=" + dateConfirmed +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public Date getDateConfirmed() {
        return dateConfirmed;
    }
}
