package Lesson5;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {
        Session session = new HibernateUtils().createSession().openSession();

        session.getTransaction().begin();

        Product product = new Product();
        product.setId(999);
        product.setName("table");
        product.setDescription("black & white");
        product.setPrice(70);

        session.save(product);

        session.getTransaction().commit();

        System.out.println("Done");
    }
}
