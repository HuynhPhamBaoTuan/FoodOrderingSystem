/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalTime;

/**
 *
 * @author giang
 */
public class ShopDTO {
    private int shopID;
    private String name;
    private String description;
    private Boolean status;
    private String shopImage;
    private String address;
    private LocalTime timeOpen;
    private LocalTime timeClose;
    private float rating;

    public ShopDTO(int shopID, String name, String description, Boolean status, String shopImage, String address, LocalTime timeOpen, LocalTime timeClose, float rating) {
        this.shopID = shopID;
        this.name = name;
        this.description = description;
        this.status = status;
        this.shopImage = shopImage;
        this.address = address;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.rating = rating;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(LocalTime timeOpen) {
        this.timeOpen = timeOpen;
    }

    public LocalTime getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(LocalTime timeClose) {
        this.timeClose = timeClose;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    
}
