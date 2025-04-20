/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class ShopRevenue {
    private int shopID;
    private String shopName;
    private String shopOwner;
    private double vnPayRevenue;
    private double codRevenue;
    private double totalRevenue;

    public ShopRevenue() {
    }
    
    

    public ShopRevenue(int shopID, String shopName, String shopOwner, double vnPayRevenue, double codRevenue, double totalRevenue) {
        this.shopID = shopID;
        this.shopName = shopName;
        this.shopOwner = shopOwner;
        this.vnPayRevenue = vnPayRevenue;
        this.codRevenue = codRevenue;
        this.totalRevenue = totalRevenue;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner;
    }

    public double getVnPayRevenue() {
        return vnPayRevenue;
    }

    public void setVnPayRevenue(double vnPayRevenue) {
        this.vnPayRevenue = vnPayRevenue;
    }

    public double getCodRevenue() {
        return codRevenue;
    }

    public void setCodRevenue(double codRevenue) {
        this.codRevenue = codRevenue;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public String toString() {
        return "ShopRevenue{" + "shopID=" + shopID + ", shopName=" + shopName + ", shopOwner=" + shopOwner + ", vnPayRevenue=" + vnPayRevenue + ", codRevenue=" + codRevenue + ", totalRevenue=" + totalRevenue + '}';
    }
}
