package model;

public class Product {

    private int productId;         // ProductID
    private String name;           // Name
    private String description;    // Description
    private double price;          // Price
    private boolean status;        // Status
    private int shopId;            // ShopID
    private int categoryId;        // CategoryID
    private int purchaseCount;     // PurchaseCount
    private double rating;         // Rating
    private String imgURL;         // ImgURL (thêm trường này)
    private Shop shop;

    public Product() {
    }

    public Product(int productId, int shopId, String name, double price) {
        this.productId = productId;
        this.shopId = shopId;
        this.name = name;
        this.price = price;
    }

    public Product(int productId, String productName) {
        this.productId = productId;
        this.name = name;
    }

    public Product(String name, String description, double price, boolean status, int shopId, int categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.shopId = shopId;
        this.categoryId = categoryId;
    }

    public Product(int productId, String name, String description, double price, boolean status, int shopId, int categoryId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.shopId = shopId;
        this.categoryId = categoryId;
    }

    public Product(String name, String description, double price, boolean status, int shopId, int categoryId, int purchaseCount, double rating) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.shopId = shopId;
        this.categoryId = categoryId;
        this.purchaseCount = purchaseCount;
        this.rating = rating;
    }

    public Product(int productId, String name, String description, double price, boolean status, int shopId, int categoryId, int purchaseCount, double rating) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.shopId = shopId;
        this.categoryId = categoryId;
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

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
