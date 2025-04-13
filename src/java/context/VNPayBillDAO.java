/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.VNPay_Bill;

public class VNPayBillDAO {

    private DBContext dbContext;

    public VNPayBillDAO() {
        dbContext = new DBContext();
    }

    public boolean createVNPayBill(VNPay_Bill bill) {
    String query = "INSERT INTO VNPay_Bill (vnpTxnRef, vnpAmount, vnpPayDate, vnpTransactionStatus, OrderId) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, bill.getVnpTxnRef()); 
        ps.setFloat(2, bill.getVnpAmount());   
        ps.setString(3, bill.getVnpPayDate());
        ps.setString(4, bill.getVnpTransactionStatus());
        ps.setInt(5, bill.getOrderId()); 

        return ps.executeUpdate() > 0; 
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public VNPay_Bill getBill(String txnRef) {
    String query = "SELECT vnpTxnRef, vnpAmount, vnpPayDate, vnpTransactionStatus, OrderId FROM VNPay_Bill WHERE vnpTxnRef = ?";
    VNPay_Bill bill = null;

    try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, txnRef); 
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            bill = new VNPay_Bill();
            bill.setVnpTxnRef(rs.getString("vnpTxnRef")); 
            bill.setVnpAmount(rs.getFloat("vnpAmount"));   
            bill.setVnpPayDate(rs.getString("vnpPayDate"));
            bill.setVnpTransactionStatus(rs.getString("vnpTransactionStatus")); 
            bill.setOrderId(rs.getInt("OrderId"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return bill;  // Return null if no bill found
}

}
