/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class ProductDTO {

    private Product product;
    private String imgURL;
    private int productId;         // ProductID
    private String name;           // Name
    private String description;    // Description
    private double price;      // Price
    private boolean status;        // Status
    private String shopName;            // ShopID
    private String categoryName;        // CategoryID
    private int purchaseCount;     // PurchaseCount
    private double rating;

    public ProductDTO(Product product, String imgURL) {
        this.product = product;
        this.imgURL = imgURL;
    }

    public ProductDTO(String imgURL, int productId, String name, String description, double price, boolean status, String shopName, String categoryName, int purchaseCount, double rating) {
        
        this.imgURL = imgURL;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.shopName = shopName;
        this.categoryName = categoryName;
        this.purchaseCount = purchaseCount;
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
}
