/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import context.RefundDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Refund;

@WebServlet(name = "RefundManagementServlet", urlPatterns = {"/refundManage"})
public class RefundManagementServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/refundManage.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account u = (Account) session.getAttribute("user");

            RefundDAO refundDAO = new RefundDAO();
            List<Refund> requestListPointOption = refundDAO.getRefundsByShopIdAndStatusAndOption(u.getShopID(), "PENDING", 1);
            List<Refund> requestListVNPayOption = refundDAO.getRefundsByShopIdAndStatusAndOption(u.getShopID(), "PENDING", 2);
            List<Refund> approvedList = refundDAO.getRefundsByShopIdAndStatus(u.getShopID(), "APPROVED");
            List<Refund> rejectedList = refundDAO.getRefundsByShopIdAndStatus(u.getShopID(), "REJECTED");

            request.setAttribute("requestListPointOption", requestListPointOption);
            request.setAttribute("requestListVNPayOption", requestListVNPayOption);
            request.setAttribute("approvedList", approvedList);
            request.setAttribute("rejectedList", rejectedList);

            request.getRequestDispatcher("WEB-INF/view/refundManage.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RefundManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
