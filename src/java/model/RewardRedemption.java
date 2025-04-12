/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phuct
 */
public class RewardRedemption {

    private int redemptionID;
    private int userID;
    private int numberOfPoint;

    public RewardRedemption(int userID, int numberOfPoint) {
        this.userID = userID;
        this.numberOfPoint = numberOfPoint;
    }

    public int getRedemptionID() {
        return redemptionID;
    }

    public void setRedemptionID(int redemptionID) {
        this.redemptionID = redemptionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNumberOfPoint() {
        return numberOfPoint;
    }

    public void setNumberOfPoint(int numberOfPoint) {
        this.numberOfPoint = numberOfPoint;
    }
}
