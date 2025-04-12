/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authenticate;

import context.AccountDAO;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/editUser"})
public class UpdateUserServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/updateUser.jsp").forward(request, response);
    }
    
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        // Kiểm tra dữ liệu đầu vào
        System.out.println("UserId: " + userId);
        System.out.println("Username: " + username);
        System.out.println("FullName: " + fullName);
        System.out.println("PhoneNumber: " + phoneNumber);
        System.out.println("Email: " + email);

        // Gọi DAO để cập nhật thông tin
        AccountDAO userDAO = new AccountDAO();
        userDAO.updateUser(userId, username, fullName, phoneNumber, email, address);

        // Cập nhật lại thông tin trong session
        HttpSession session = request.getSession();
        Account updatedUser = userDAO.getUserById(userId); // Cần có phương thức này trong DAO
        session.setAttribute("user", updatedUser);

        // Vô hiệu hóa cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Chuyển hướng sau khi cập nhật thành công
        response.sendRedirect("account");
    } catch (NumberFormatException e) {
        e.printStackTrace();
        response.getWriter().println("Invalid input for user ID, shop ID or role.");
    } catch (Exception e) {
        e.printStackTrace();
        response.getWriter().println("Error updating user.");
    }
}

}