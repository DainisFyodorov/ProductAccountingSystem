package database;

import system.Category;
import system.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryDatabase implements Database {
    private HashMap<Long, Product> products = new HashMap<Long, Product>();
    private Long currentID = (long) 0;

    @Override
    public void add(Product product) {
        if(!products.containsValue(product)) {
            products.put(currentID, product);
            product.setProductID(currentID);
            currentID++;
        }
    }

    @Override
    public void remove(Product product) {
        if(products.containsValue(product)) {
            products.remove(product.getProductID());
        }
    }

    @Override
    public Product getProductByID(Long productID) {
        if(products.containsKey(productID)) {
            return products.get(productID);
        }

        return null;
    }

    @Override
    public void removeProductByID(Long productID) {
        if(products.containsKey(productID)) {
            products.remove(productID);
        }
    }

    @Override
    public List<Product> getProductsByCategory(Category productCategory) {
        List<Product> result = new ArrayList<Product>();
        for(Product product : products.values()) {
            if(product.getProductCategory() == productCategory) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();
        for(Product product : products.values()) {
            productList.add(product);
        }
        return productList;
    }
}
