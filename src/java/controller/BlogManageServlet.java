/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import context.AccountDAO;
import context.PostDAO;
import jakarta.servlet.RequestDispatcher;
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
import model.Post;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "BlogManageServlet", urlPatterns = {"/blog-manage"})
public class BlogManageServlet extends HttpServlet {

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
            out.println("<title>Servlet BlogManageServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogManageServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account user = (Account)session.getAttribute("user");
        PostDAO postDAO = new PostDAO();
        AccountDAO userDAO = new AccountDAO();
        List<Post> postList = null;
        Post newPost = null;
        String fullNameNewPost = null;
        String avatarURL = null;
        String avatarURLNewPost = null;

        try {
            boolean isConnected = postDAO.checkConnection();
            if (isConnected) {
                postList = postDAO.getPostsByUserID(user.getUserID());
                newPost = postDAO.getLatestPost();

                if (newPost != null) {
                    avatarURLNewPost = userDAO.getAvatarByUserId(newPost.getUserID());
                    fullNameNewPost = userDAO.getFullNameByUserId(newPost.getUserID());
                    avatarURL = userDAO.getAvatarByUserId(newPost.getUserID());
                    newPost.setAvtUserImg(avatarURL);  // Set the avatar URL in the Post object
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("postList", postList);
        request.setAttribute("newPost", newPost);
        request.setAttribute("fullNameNewPost", fullNameNewPost);
        request.setAttribute("avatarURLNewPost", avatarURLNewPost);

        System.out.println(avatarURL);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/blog-manage.jsp");
        dispatcher.forward(request, response);
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
