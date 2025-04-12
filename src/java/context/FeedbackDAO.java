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
import model.Feedback;

/**
 *
 * @author giang
 */
public class FeedbackDAO {
    private DBContext dbContext;

    public FeedbackDAO() {
        dbContext = new DBContext();
    }
    public boolean addFeedback(Feedback c) {
        String query = "INSERT INTO Feedback (UserID, ProductID, Rating, Comment) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, c.getUserId());
            ps.setInt(2, c.getFeedbackId());
            ps.setInt(3, c.getRating());
            ps.setString(4, c.getComment());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Feedback getFeedbackByFeedbackID(int feedbackId) {
        Feedback fav = null;
        String sql = "SELECT * FROM Feedback WHERE FeedbackID=?";
        try (Connection con = dbContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, feedbackId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fav = new Feedback();
                fav.setUserId(rs.getInt("UserID"));
                fav.setProductId(rs.getInt("ProductID"));
                fav.setFeedbackId(rs.getInt("FeedbackID"));
                fav.setRating(rs.getInt("Rating"));
                fav.setComment(rs.getString("Comment"));
                fav.setCreatedDate(rs.getDate("CreatedDate"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return fav;
    }

    public List<Feedback> getFeedbackByProductID(int id) {
        List<Feedback> list = new ArrayList<>();
        String query = "SELECT * FROM Feedback where ProductID = ? ";
        try (Connection con = dbContext.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback c = new Feedback();
                c.setUserId(rs.getInt("UserID"));
                c.setProductId(rs.getInt("ProductID"));
                c.setFeedbackId(rs.getInt("FeedbackID"));
                c.setRating(rs.getInt("Rating"));
                c.setComment(rs.getString("Comment"));
                c.setCreatedDate(rs.getDate("CreatedDate"));
                list.add(c);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("not found products");
        return null;
    }

    public boolean deleteFeedback(int feedbackID) {
        String query = "DELETE FROM Feedback WHERE FeedbackID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, feedbackID);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean updateFeedback(int feedbackID, String comment, int rating) {
    String query = "UPDATE Feedback SET Comment = ?, Rating = ? WHERE FeedbackID = ?";
    try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, comment);
        ps.setInt(2, rating);
        ps.setInt(3, feedbackID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0; 
    } catch (Exception e) {
        e.printStackTrace(); 
        return false;
    }
}
}



