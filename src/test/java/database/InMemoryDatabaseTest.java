package database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import system.Category;
import system.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryDatabaseTest {

    private static Database database = new InMemoryDatabase();
    private static Product product = new Product("Apple", BigDecimal.valueOf(100.40), Category.FRUIT, BigDecimal.valueOf(0), "No description");

    @BeforeAll
    static void before() {
        Database database = new InMemoryDatabase();
    }

    @Test
    void add() {
        database.add(product);
        assertEquals(product, database.getProductByID((long) 0));
    }

    @Test
    void remove() {
        database.add(product);
        database.remove(product);
        assertEquals(null, database.getProductByID((long) 0));
    }

    @Test
    void getProductByID() {
        database.add(product);
        assertEquals(product, database.getProductByID((long) 0));
    }

    @Test
    void removeProductByID() {
        database.add(product);
        database.removeProductByID(product.getProductID());
        assertEquals(null, database.getProductByID((long) 0));
    }

    @Test
    void getProductsByCategory() {
        Product productTwo = new Product("Orange", BigDecimal.valueOf(12.99), Category.FRUIT, BigDecimal.valueOf(0), "-");
        Product productThree = new Product("Carrot", BigDecimal.valueOf(13.99), Category.VEGETABLE, BigDecimal.valueOf(0), "-");

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(productTwo);

        database.add(product);
        database.add(productTwo);
        database.add(productThree);

        assertEquals(products, database.getProductsByCategory(Category.FRUIT));
    }

    @Test
    void getAllProducts() {
        Product productTwo = new Product("Orange", BigDecimal.valueOf(12.99), Category.FRUIT, BigDecimal.valueOf(0), "-");
        Product productThree = new Product("Carrot", BigDecimal.valueOf(13.99), Category.VEGETABLE, BigDecimal.valueOf(0), "-");

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(productTwo);
        products.add(productThree);

        database.add(product);
        database.add(productTwo);
        database.add(productThree);

        assertEquals(products, database.getAllProducts());
    }
}