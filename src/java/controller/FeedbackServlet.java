/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import context.FeedbackDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Account;
import model.Feedback;

public class FeedbackServlet extends HttpServlet {

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
            out.println("<title>Servlet FeedbackServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        switch(action){
            case "add" -> addFeedback(request, response);
            case "delete" -> deleteFeedback(request, response); 
            case "update" -> updateFeedback(request, response);  
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    private void addFeedback(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Account user = (Account) request.getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("productID"));
        String comment = request.getParameter("comment");
        int rating = Integer.parseInt(request.getParameter("rating"));
        FeedbackDAO fdao = new FeedbackDAO();
        Feedback f = new Feedback();
        f.setComment(comment);
        f.setFeedbackId(id);
        f.setUserId(user.getUserID());
        f.setRating(rating);
        fdao.addFeedback(f);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/food-detail?productId=" + id);
        dispatcher.forward(request, response);
    }
    
    private void deleteFeedback(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int fid = Integer.parseInt(request.getParameter("feebackID"));
        int id = Integer.parseInt(request.getParameter("productID"));
        FeedbackDAO fdao = new FeedbackDAO();
        fdao.deleteFeedback(fid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/food-detail?productId=" + id);
        dispatcher.forward(request, response);
    }
    
    private void updateFeedback(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int fid = Integer.parseInt(request.getParameter("feebackID"));
        int id = Integer.parseInt(request.getParameter("productID"));
        String comment = request.getParameter("comment");
        int rating = Integer.parseInt(request.getParameter("rating"));
        
        FeedbackDAO fdao = new FeedbackDAO();
        fdao.updateFeedback(fid, comment, rating);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/food-detail?productId=" + id);
        dispatcher.forward(request, response);
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}