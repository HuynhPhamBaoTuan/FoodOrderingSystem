/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import context.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Post;

@WebServlet(name = "AdminPostServlet", urlPatterns = {"/admin-post"})
public class AdminPostServlet extends HttpServlet {
   
    private PostDAO postDAO = new PostDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Admin_Post_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin_Post_Servlet at " + request.getContextPath() + "</h1>");
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
        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "listPosts";
            }
            switch (action) {
                case "listPosts":
                    listPosts(request, response);
                    break;
                case "lockIllegalPost":
                    lockIllegalPost(request, response);
                    break;
                case "unlockPost":
                    unlockPost(request, response);
                    break;
                default:
                    listPosts(request, response);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminPostServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // list post
    private void listPosts(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, Exception {
     List<Post> list = postDAO.getAllPostsData();
     // In ra số lượng bài đăng để kiểm tra xem có dữ liệu không
     System.out.println("Number of posts retrieved: " + list.size());
     
     // Kiểm tra nội dung của bài đăng đầu tiên (nếu có)
     if (!list.isEmpty()) {
         System.out.println("First post ID: " + list.get(0).getPostID());
     }

     request.setAttribute("posts", list);
     request.getRequestDispatcher("WEB-INF/view/admin-post.jsp").forward(request, response);
 }


    private void lockIllegalPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            // Lấy tham số id từ URL
            String idParam = request.getParameter("id");
            System.out.println("ID Param: " + idParam); // Debug để in ra giá trị id

            if (idParam == null || idParam.isEmpty()) {
                throw new IllegalArgumentException("Product ID is missing!");
            }

            int postID = Integer.parseInt(idParam);
            boolean flag = postDAO.lockIllegalPost(postID);

            String msg;
            if (flag) {
                msg = "Lock successfully!";
            } else {
                msg = "Fail to lock post!";
            }

            request.getSession().setAttribute("msg", msg);
            response.sendRedirect("admin-post?action=listPosts");

        } catch (NumberFormatException e) {
            // Nếu ID không hợp lệ (không phải là số)
            request.getSession().setAttribute("msg", "Invalid post ID.");
            response.sendRedirect("admin-post?action=listPosts");
        } catch (Exception e) {
            // Xử lý các lỗi khác
            e.printStackTrace();
            request.getSession().setAttribute("msg", "Error occurred while locking post.");
            response.sendRedirect("admin-post?action=listPosts");
        }
    }
    
    
    // unlock Post
    private void unlockPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            // Lấy tham số id từ URL
            String idParam = request.getParameter("id");
            System.out.println("ID Param: " + idParam); // Debug để in ra giá trị id

            if (idParam == null || idParam.isEmpty()) {
                throw new IllegalArgumentException("Product ID is missing!");
            }

            int postID = Integer.parseInt(idParam);
            boolean flag = postDAO.unlockPost(postID);

            String msg;
            if (flag) {
                msg = "Unlock successfully!";
            } else {
                msg = "Fail to unlock post!";
            }

            request.getSession().setAttribute("msg", msg);
            response.sendRedirect("admin-post?action=listPosts");

        } catch (NumberFormatException e) {
            // Nếu ID không hợp lệ (không phải là số)
            request.getSession().setAttribute("msg", "Invalid post ID.");
            response.sendRedirect("admin-post?action=listPosts");
        } catch (Exception e) {
            // Xử lý các lỗi khác
            e.printStackTrace();
            request.getSession().setAttribute("msg", "Error occurred while unlocking post.");
            response.sendRedirect("admin-post?action=listPosts");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
