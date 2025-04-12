/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Account {

    private int userID; // not null 
    private String userName; //  not null
    private String password; // not null
    private String fullName; //  not null
    private String phoneNumber;
    private String email; // Optional
    private String address; // Optional
    private String avtImg; // Optional
    private int shopID; // Optional
    private int role; // Not null
    private String code;
    private boolean status;
    

    public Account() {
    }

    public Account(String userName) {
        this.userName = userName;
    }

    public Account(String userName, String password, String fullName, String phoneNumber, String email, String address, int role) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public Account(String userName, String password, String fullName, String phoneNumber, String email, String address, String avtImg, int role) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.avtImg = avtImg;
        this.role = role;
    }

    public Account(int userID, String userName, String password, String fullName, String phoneNumber, String email, int role) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }

    public Account(int userID, String userName, String password, String fullName, String phoneNumber, String email, String address, String avtImg, int shopID, int role) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.avtImg = avtImg;
        this.shopID = shopID;
        this.role = role;
    }

    public Account(int userID, String userName, String password, String fullName, String phoneNumber, String email, String address, String avtImg, int shopID, int role, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.avtImg = avtImg;
        this.shopID = shopID;
        this.role = role;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvtImg() {
        return avtImg;
    }

    public void setAvtImg(String avtImg) {
        this.avtImg = avtImg;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
     public Account(String userName, String password, String fullName, String phoneNumber, String email, String address, int shopID, int role) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.shopID = shopID;
        this.role = role;
    }

}
