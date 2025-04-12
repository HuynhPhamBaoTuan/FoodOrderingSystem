/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class ProductImage {
    private int imageID;
    private int productID;
    private boolean isAvatar;
    private String imgURL;

    public ProductImage(int imageID, int productID, boolean isAvatar, String imgURL) {
        this.imageID = imageID;
        this.productID = productID;
        this.isAvatar = isAvatar;
        this.imgURL = imgURL;
    }

    public ProductImage(int productID, boolean isAvatar, String imgURL) {
        this.productID = productID;
        this.isAvatar = isAvatar;
        this.imgURL = imgURL;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public boolean isAvatar() {
        return isAvatar;
    }

    public void setIsAvatar(boolean isAvatar) {
        this.isAvatar = isAvatar;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
    
}