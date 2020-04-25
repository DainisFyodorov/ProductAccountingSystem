package database;

import system.Category;
import system.Product;

import java.util.List;

public interface Database {
    void add(Product product);
    void remove(Product product);

    Product getProductByID(Long productID);
    void removeProductByID(Long productID);
    List<Product> getProductsByCategory(Category productCategory);

    List<Product> getAllProducts();
}
