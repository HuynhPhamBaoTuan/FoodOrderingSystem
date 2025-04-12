/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Favourite;

/**
 *
 * @author giang
 */
public class FavouriteDAO {
    private DBContext dbContext;

    public FavouriteDAO() {
        dbContext = new DBContext();
    }
    public boolean addToWishlist(Favourite c) {
        String query = "INSERT INTO Favourite (UserID, ProductID) VALUES (?, ?)";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, c.getUserID());
            ps.setInt(2, c.getProductID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Favourite getFavouriteByUserIdandProductID(int userId, int productId) {
        Favourite fav = null;
        String sql = "SELECT * FROM Favourite WHERE UserID=? and ProductID=?";
        try (Connection con = dbContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fav = new Favourite();
                fav.setUserID(rs.getInt("UserID"));
                fav.setProductID(rs.getInt("ProductID"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return fav;
    }

    public List<Favourite> getWishlistByUserID(int id) {
        List<Favourite> cart = new ArrayList<>();
        String query = "SELECT * FROM Favourite where UserID = ? ";
        try (Connection con = dbContext.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Favourite c = new Favourite(
                        rs.getInt("UserID"),
                        rs.getInt("ProductID")
                );
                cart.add(c);

            }
            return cart;

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("not found products");
        return null;
    }

    public boolean deleteWishlistProduct(int productID, int userID) {
        String query = "DELETE FROM Favourite WHERE ProductID = ? AND UserID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productID);
            ps.setInt(2, userID);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
