/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;


public class CategoryDAO {

    private DBContext dbContext;
    private List<Category> categories = new ArrayList<>();

    public CategoryDAO() {
        dbContext = new DBContext();
    }

    public boolean checkConnection() throws Exception {
        try (Connection conn = dbContext.getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Connection failed
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Categories";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("Type"),
                        rs.getString("Description")
                );
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    
        public String getCategoryNameByID(int categoryID) {
        String categoryName = null;
        String query = "SELECT Type FROM Categories WHERE CategoryID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, categoryID); // Đặt giá trị CategoryID vào câu truy vấn
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoryName = rs.getString("Type");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryName;
    }
}
