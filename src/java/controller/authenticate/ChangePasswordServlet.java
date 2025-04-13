/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.authenticate;

import context.AccountDAO;
import static controller.authenticate.RegisterServlet.isValidPassword;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import util.PasswordUtil;

public class ChangePasswordServlet extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePasswordServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "showUpdatePage";
        }
        switch(action){
            case "showUpdatePage":
                showUpdatePage(request, response);
                break;
            case "updatePass":
                updatePass(request, response);
                break;
        }
    } 
    
    protected void showUpdatePage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/change-password.jsp").forward(request, response);
    }
    
    protected void updatePass(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Account user = (Account) request.getSession().getAttribute("user");
        
        if(user == null){
            response.sendRedirect("/WEB-INF/view/login.jsp");
            return;
        }
        
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        AccountDAO userDAO = new AccountDAO();
        String hashedPassword = user.getPassword();
        
        // kiểm tra mật khẩu hiện tại có đúng ko
        if(!PasswordUtil.checkPassword(currentPassword, hashedPassword)){
             request.setAttribute("error", "Current password is incorrect");
            request.getRequestDispatcher("/WEB-INF/view/change-password.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra mật khẩu mới và mật khẩu xác nhận có khớp không
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New passwords do not match");
            request.getRequestDispatcher("/WEB-INF/view/change-password.jsp").forward(request, response);
            return;
        }
        
        if (!isValidPassword(newPassword)) {
            request.setAttribute("error", "Invalid password. Need to have both number and letter and more than 8 characters.");
            request.getRequestDispatcher("/WEB-INF/view/change-password.jsp").forward(request, response);
            return;
        }
        
        // Cập nhật mật khẩu mới
        String hashedNewPassword = PasswordUtil.hashPassword(newPassword);
        user.setPassword(hashedNewPassword); // Ensure that 'password' field in user object is updated
        userDAO.changePasswordByUserID(user);

        request.setAttribute("success", "Password changed successfully");
        request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
