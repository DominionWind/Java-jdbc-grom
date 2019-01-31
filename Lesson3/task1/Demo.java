package Lesson3.task1;

public class Demo {
    public static void main(String[] args) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        Solution solution = new Solution();

        Product product = new Product(10, "testgdfgdfg", "test descriptiondfgdfgdfg", 9888889);
        Product product1 = new Product(11, "Maze", "Minos", 1489);

//        productDAO.save(product1);

//        productDAO.delete(10);

//        productDAO.update(product);

        System.out.println(solution.findProductByPrice(100,99));

        System.out.println(solution.findProductsByName("aze"));

        System.out.println(solution.findProductWithEmptyDescription());

//        System.out.println(productDAO.getProducts());

    }
}
