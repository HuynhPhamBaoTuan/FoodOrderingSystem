package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.RewardRedemption;

public class RewardRedemptionDAO {

    private DBContext dbContext;

    public RewardRedemptionDAO() {
        dbContext = new DBContext();
    }

    public boolean checkConnection() throws Exception {
        try (Connection conn = dbContext.getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getPointsByUserID(int userID) throws Exception {
        String query = "SELECT NumberOfPoint FROM RewardRedemption WHERE UserID = ?";
        int points = 0;

        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    points = rs.getInt("NumberOfPoint");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return points;
    }

    public boolean createRewardRedemption(int userID, int initialPoints) throws Exception {
        String query = "INSERT INTO RewardRedemption (UserID, NumberOfPoint) VALUES (?, ?)";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.setInt(2, initialPoints);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePoints(int userID, int pointsToAdd) {
        String query = "UPDATE RewardRedemption SET NumberOfPoint = NumberOfPoint + ? WHERE UserID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, pointsToAdd);
            ps.setInt(2, userID);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePointsCancelOrder(int userID, int points) {
        String query = "UPDATE RewardRedemption SET NumberOfPoint = NumberOfPoint - ? WHERE UserID = ? AND NumberOfPoint >= ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, points);
            ps.setInt(2, userID);
            ps.setInt(3, points); // Điều kiện để đảm bảo không trừ điểm quá mức
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isRewardRegistered(int userID) {
        String query = "SELECT COUNT(*) FROM RewardRedemption WHERE UserID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean redeemPoints(int userId, int pointsRequired) {
        String checkPointsQuery = "SELECT NumberOfPoint FROM RewardRedemption WHERE UserID = ?";
        String updatePointsQuery = "UPDATE RewardRedemption SET NumberOfPoint = NumberOfPoint - ? WHERE UserID = ?";

        try (Connection conn = dbContext.getConnection(); PreparedStatement checkPs = conn.prepareStatement(checkPointsQuery)) {
            checkPs.setInt(1, userId);
            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next()) {
                    int currentPoints = rs.getInt("NumberOfPoint");

                    if (currentPoints >= pointsRequired) {
                        try (PreparedStatement updatePs = conn.prepareStatement(updatePointsQuery)) {
                            updatePs.setInt(1, pointsRequired);
                            updatePs.setInt(2, userId);
                            updatePs.executeUpdate();
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
