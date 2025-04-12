/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import context.AccountDAO;
import context.OrderDAO;
import context.RefundDAO;
import context.VNPayBillDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.Refund;
import model.VNPay_Bill;
import util.Email;
import util.Utility;

/**
 *
 * @author phuct
 */
@WebServlet(name = "RefundDetailsServlet", urlPatterns = {"/refundDetails"})
public class RefundDetailsServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RefundDetailsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RefundDetailsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String refundIDParam = request.getParameter("refundID");

        RefundDAO refundDAO = new RefundDAO();
        OrderDAO orderDAO = new OrderDAO();
        VNPayBillDAO vnpayBillDAO = new VNPayBillDAO();

        if (refundIDParam == null || refundIDParam.isEmpty()) {
            request.setAttribute("error", "Refund ID is missing.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        try {
            int refundOption = refundDAO.getrefundOptionByRefundId(Integer.parseInt(refundIDParam));

            if (refundOption == 1) {
                Refund refundInfo = refundDAO.getRefundById(Integer.parseInt(refundIDParam));
                int orderID = refundDAO.getOrderIdByRefundId(Integer.parseInt(refundIDParam));
                double totalAmount = orderDAO.getTotalByOrderID(orderID);
                request.setAttribute("totalAmount", totalAmount);

                request.setAttribute("refundInfo", refundInfo);
                request.getRequestDispatcher("WEB-INF/view/refundDetailPoint.jsp").forward(request, response);
                return;
            }

            int orderID = refundDAO.getOrderIdByRefundId(Integer.parseInt(refundIDParam));
            String paymentID = orderDAO.getPaymentIDByOrderID(orderID);
            VNPay_Bill vnpayBill = vnpayBillDAO.getBill(paymentID);

            if (vnpayBill == null) {
                request.setAttribute("error", "No VNPay bill found for the given payment ID.");
                request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
                return;
            }

            Refund refundInfo = refundDAO.getRefundById(Integer.parseInt(refundIDParam));

            if (refundInfo == null) {
                request.setAttribute("error", "No refund information found for the given refund ID.");
                request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
                return;
            }

            DecimalFormat formatter = new DecimalFormat("#,###,###,###");
            String formattedAmount = formatter.format(vnpayBill.getVnpAmount());

            request.setAttribute("refundInfo", refundInfo);
            request.setAttribute("vnpayBill", vnpayBill);
            request.setAttribute("formattedAmount", formattedAmount);

            request.getRequestDispatcher("WEB-INF/view/refundDetails.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            Logger.getLogger(RefundDetailsServlet.class.getName()).log(Level.SEVERE, "Invalid refund ID format.", e);
            request.setAttribute("error", "Invalid refund ID format.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(RefundDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "An error occurred while processing your request.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        switch (action) {
            case "reject":
                CancelRefundRequest(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void CancelRefundRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String refundId = request.getParameter("refundId");

        RefundDAO refundDAO = new RefundDAO();
        AccountDAO aDAO = new AccountDAO();
        Order order = refundDAO.getOrderByRefundId(Integer.parseInt(refundId));

        try {
            int orderID = refundDAO.getOrderIdByRefundId(Integer.parseInt(refundId));
            refundDAO.updateRefundStatus(Integer.parseInt(refundId), "REJECTED");
            String email = aDAO.getUserById(order.getUserId()).getEmail();
            String content = "We regret to inform you that your refund request has been declined. If you have any concerns or questions, please feel free to contact our shop at the following \nStore Address: " + Utility.getShopAddressByOrderID(orderID);
            Email.sendEmailNotifying(email, content);
        } catch (Exception ex) {
            Logger.getLogger(RefundDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("refundManage");
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
