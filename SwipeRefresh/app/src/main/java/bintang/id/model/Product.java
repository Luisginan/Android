/**
 * Created by bintang on 3/8/2016.
 * Model class
 */

package bintang.id.model;

public class Product {
    private int productImage;
    private String productName;
    private String productPrice;

    public Product(int productImage, String productName, String productPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }
}


