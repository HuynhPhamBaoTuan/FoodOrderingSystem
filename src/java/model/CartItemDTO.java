package model;

import model.Product;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author LENOVO
 */
public class CartItemDTO {

    private Product product;
    private int quantity;
    private String imgURL;
    private double price;

    public CartItemDTO() {
    }

    public CartItemDTO(Product product, int quantity, String imgURL) {
        this.product = product;
        this.quantity = quantity;
        this.imgURL = imgURL;
    }

    public CartItemDTO(Product product, String imgURL) {
        this.product = product;
        this.imgURL = imgURL;
    }

    public CartItemDTO(Product product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
