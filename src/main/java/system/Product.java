package system;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    String productName;
    Long productID;
    BigDecimal productPrice;
    Category productCategory;

    BigDecimal productDiscount;
    String productDescription;

    public Product(String productName, BigDecimal productPrice, Category productCategory, BigDecimal productDiscount, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productDiscount = productDiscount;
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Long getProductID() {
        return productID;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductDiscount(BigDecimal productDiscount) {
        this.productDiscount = productDiscount;
    }

    public BigDecimal getProductDiscount() {
        return productDiscount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productID=" + productID +
                ", productPrice=" + productPrice +
                ", productCategory=" + productCategory +
                ", productDiscount=" + productDiscount +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName) &&
                Objects.equals(productID, product.productID) &&
                Objects.equals(productPrice, product.productPrice) &&
                productCategory == product.productCategory &&
                Objects.equals(productDiscount, product.productDiscount) &&
                Objects.equals(productDescription, product.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productID, productPrice, productCategory, productDiscount, productDescription);
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
