/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class CartItem {
    Product product;
    private int cartItemID;
    private int userID;
    private int productID;
    private int quantity;
    private int shopID;

    public CartItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
  public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }    
    public CartItem(int cartItemID, int userID, int productID, int quantity, int shopID) {
        this.cartItemID = cartItemID;
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
        this.shopID = shopID;
    }

    public CartItem(int userID, int productID, int quantity, int shopID) {
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
        this.shopID = shopID;
    }

    public int getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(int cartItemID) {
        this.cartItemID = cartItemID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }
    
    
    
}
