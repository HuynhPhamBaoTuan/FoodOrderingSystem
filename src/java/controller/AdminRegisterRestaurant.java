/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import context.AccountDAO;
import context.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Shop;
import util.Email;

@WebServlet(name = "AdminRegisterRestaurant", urlPatterns = {"/admin-register-restaurant"})
public class AdminRegisterRestaurant extends HttpServlet {

    private ShopDAO shopDAO = new ShopDAO();
    private AccountDAO accountDAO = new AccountDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminRegisterRestaurant</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminRegisterRestaurant at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list-registered";
        }
        switch (action) {
            case "list-registered":
                listRegistered(request, response);
                break;
            case "view-details":
                viewDetails(request, response);
                break;
            case "approve-register":
                approveRegister(request, response);
                break;
            case "show-reject-form":
                showRejectForm(request, response);
                break;
            case "reject-register":
                rejectRegister(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listRegistered(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Shop> shops = shopDAO.getRegisteredShopToProcess();
        request.setAttribute("shops", shops);
        System.out.println("Sao khi đăng kí t có");
        request.getRequestDispatcher("WEB-INF/view/admin-register-restaurant.jsp").forward(request, response);
    }

    private void viewDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int shopID = Integer.parseInt(request.getParameter("shopId"));
        Shop shop = shopDAO.getShopByID(shopID);
        Account shopOwner = accountDAO.getShopOwnerByShopID(shopID);
        request.setAttribute("shop", shop);
        request.setAttribute("owner", shopOwner);
        request.getRequestDispatcher("WEB-INF/view/admin-register-restaurant-details.jsp").forward(request, response);
    }

    private void approveRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int shopID = Integer.parseInt(request.getParameter("shopId"));
        Account shopOwner = accountDAO.getShopOwnerByShopID(shopID);
        int userID = shopOwner.getUserID();
        String email = shopOwner.getEmail();
        String ownerName = shopOwner.getFullName();

        // update shop status = 1 by shopID
        if (shopDAO.updateShopStatusAfterApprove(shopID)) {
            System.out.println("update thành công");
        }

        // Send approve email
        if (Email.sendEmailToApproveRegisterRestaurant(ownerName ,email)) {
            request.setAttribute("message1", "Approve successfully");
            request.getRequestDispatcher("admin-register-restaurant?action=list-registered").forward(request, response);
        } else {
            request.setAttribute("message2", "Failed to approve.");
            request.getRequestDispatcher("WEB-INF/view/admin-register-restaurant.jsp").forward(request, response);
        }
    }

    private void showRejectForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int shopID = Integer.parseInt(request.getParameter("shopId"));
        request.setAttribute("shopId", shopID);
        request.getRequestDispatcher("WEB-INF/view/reject-restaurant-form.jsp").forward(request, response);
    }

    private void rejectRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int shopID = Integer.parseInt(request.getParameter("shopId"));
        String reason = request.getParameter("reason");
        Account shopOwner = accountDAO.getShopOwnerByShopID(shopID);
        int userID = shopOwner.getUserID();
        String email = shopOwner.getEmail();
        String ownerName = shopOwner.getFullName();

         //delete shopOwner by userID
        if (accountDAO.deleteUser(userID)) {
            System.out.println("Delete thành công");
        };
        
        // delete shop by shopID
        if(shopDAO.deleteRestaurant(shopID)){
            System.out.println("Shop deleted successfully!");
        }
        
        // Send reject email
        if (Email.sendEmailToRejectRegisterRestaurant(ownerName,email, reason)) {
            request.setAttribute("message1", "Reject successfully");
            request.getRequestDispatcher("admin-register-restaurant?action=list-registered").forward(request, response);
        } else {
            request.setAttribute("message2", "Failed to reject.");
            request.getRequestDispatcher("WEB-INF/view/admin-register-restaurant.jsp").forward(request, response);
        }
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
