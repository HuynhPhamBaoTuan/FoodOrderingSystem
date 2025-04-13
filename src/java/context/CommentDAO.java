/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Comment;


public class CommentDAO {

    private DBContext dbContext;

    public CommentDAO() {
        dbContext = new DBContext();
    }

    // Method to add a comment
    public boolean addComment(Comment comment) throws Exception {
        String sql = "INSERT INTO Comment (UserID, PostID, Content, CreatedDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, comment.getUserID());
            ps.setInt(2, comment.getPostID());
            ps.setString(3, comment.getContent());
            ps.setTimestamp(4, new Timestamp(comment.getCreatedDate().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to update a comment
    public boolean updateComment(int commentID, String newContent) throws Exception {
        String sql = "UPDATE Comment SET Content = ? WHERE CommentID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newContent);
            ps.setInt(2, commentID);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a comment
    public boolean deleteComment(int commentID) throws Exception {
        String sql = "DELETE FROM Comment WHERE CommentID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, commentID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to retrieve a comment by ID
    public Comment getCommentByID(int commentID) throws Exception {
        String sql = "SELECT * FROM Comment WHERE CommentID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, commentID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Comment(
                            rs.getInt("CommentID"),
                            rs.getInt("UserID"),
                            rs.getInt("PostID"),
                            rs.getString("Content"),
                            rs.getTimestamp("CreatedDate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to retrieve all comments for a specific post with user details
    public List<Comment> getCommentsByPostID(int postID) throws Exception {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT c.*, u.FullName, u.AvtImg FROM Comment c "
                + "JOIN Users u ON c.UserID = u.UserID WHERE c.PostID = ? ORDER BY c.CreatedDate DESC";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, postID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comment comment = new Comment(
                            rs.getInt("CommentID"),
                            rs.getInt("UserID"),
                            rs.getString("FullName"),
                            rs.getString("AvtImg"),
                            rs.getInt("PostID"),
                            rs.getString("Content"),
                            rs.getTimestamp("CreatedDate")
                    );
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    
    // Method to delete spam comment
    public boolean deleteSpamComment(int userID, String content) throws Exception {
        String sql = " DELETE Comment WHERE UserID= ? AND Content =?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ps.setString(2, content);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
