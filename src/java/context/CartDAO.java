package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.CartItem;
import model.CartItemDTO;
import model.Product;

public class CartDAO {

    private DBContext dbContext;

    public CartDAO() {
        dbContext = new DBContext();
    }

    public boolean addToCart(CartItem c) {
        String query = "INSERT INTO CartItem (UserID, ProductID, Quantity, ShopID) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, c.getUserID());
            ps.setInt(2, c.getProductID());
            ps.setInt(3, c.getQuantity());
            ps.setInt(4, c.getShopID());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CartItem> getCartByUserID(int id) {
        List<CartItem> cart = new ArrayList<>();
        String query = "SELECT * FROM CartItem where UserID = ? ";
        try (Connection con = dbContext.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem c = new CartItem(
                        rs.getInt("CartItemID"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity"),
                        rs.getInt("ShopID")
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

    public boolean deleteCartProduct(int productID, int userID) {
        String query = "DELETE FROM CartItem WHERE ProductID = ? AND UserID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productID);
            ps.setInt(2, userID);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateCartItemQuantity(int userId, int productId, int quantity) {
        String query = "UPDATE CartItem SET Quantity = ? WHERE UserID = ? AND ProductID = ?";
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setInt(3, productId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<CartItemDTO> getCartItems(int userID) {
    List<CartItemDTO> cartItems = new ArrayList<>();
    // Cập nhật câu truy vấn để bao gồm giá sản phẩm
    String query = "SELECT p.ProductID, p.ShopID, p.Name, p.Price, ci.Quantity " +
                   "FROM CartItem ci " +
                   "JOIN Product p ON ci.ProductID = p.ProductID " +
                   "WHERE ci.UserID = ?";
    try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int productID = rs.getInt("ProductID");
            int shopID = rs.getInt("ShopID");
            String productName = rs.getString("Name");
            int quantity = rs.getInt("Quantity");
            double price = rs.getDouble("Price"); // Lấy giá sản phẩm

            // Tạo Product và CartItemDTO
            Product product = new Product(productID, shopID, productName, price); // Cập nhật constructor để nhận giá
            CartItemDTO cartItemDTO = new CartItemDTO(product, quantity, price); // Cập nhật để bao gồm giá

            // Lấy hình ảnh sản phẩm
            String productImageUrl = getProductImage(productID);
            cartItemDTO.setImgURL(productImageUrl);

            cartItems.add(cartItemDTO);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return cartItems;
}


// Phương thức lấy URL hình ảnh của sản phẩm
    private String getProductImage(int productID) {
        String imageUrl = null;
        String query = "SELECT ImgURL FROM ProductImage WHERE ProductID = ? AND IsAvatar = 1";

        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                imageUrl = rs.getString("ImgURL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageUrl;
    }

// Phương thức nhóm CartItemDTO theo ShopID
    public Map<Integer, List<CartItemDTO>> groupCartItemsByShop(int userID) {
        List<CartItemDTO> cartItems = getCartItems(userID);
        Map<Integer, List<CartItemDTO>> shopCartItemsMap = new HashMap<>();

        for (CartItemDTO cartItemDTO : cartItems) {
            Product product = cartItemDTO.getProduct();
            int shopID = product.getShopId();

            shopCartItemsMap.putIfAbsent(shopID, new ArrayList<>());
            shopCartItemsMap.get(shopID).add(cartItemDTO);
        }

        return shopCartItemsMap;
    }

}
