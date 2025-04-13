/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import context.PostDAO;
import context.ProductDAO;
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
import model.Product;

@WebServlet(name = "AdminProductServlet", urlPatterns = {"/admin-item"})
public class AdminProductServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet Xin chào</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listProducts";
        }
        switch (action) {
            case "listProducts":
                listProducts(request, response);
                break;
            case "lockIllegalProduct":
                lockIllegalProduct(request, response);
                break;
            case "unlockProduct":
                unLockProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // list product
    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> list = productDAO.getAllProductsFromInventory();
        request.setAttribute("products", list);
        request.getRequestDispatcher("WEB-INF/view/admin-item.jsp").forward(request, response);
    }

    private void lockIllegalProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số id từ URL
            String idParam = request.getParameter("id");
            System.out.println("ID Param: " + idParam); // Debug để in ra giá trị id

            if (idParam == null || idParam.isEmpty()) {
                throw new IllegalArgumentException("Product ID is missing!");
            }

            int productID = Integer.parseInt(idParam);
            boolean flag = productDAO.lockIllegalProduct(productID);

            String msg;
            if (flag) {
                msg = "Lock successfully!";
            } else {
                msg = "Fail to delete product!";
            }

            request.getSession().setAttribute("msg", msg);
            response.sendRedirect("admin-item?action=listProducts");

        } catch (NumberFormatException e) {
            // Nếu ID không hợp lệ (không phải là số)
            request.getSession().setAttribute("msg", "Invalid product ID.");
            response.sendRedirect("admin-item?action=listProducts");
        } catch (Exception e) {
            // Xử lý các lỗi khác
            e.printStackTrace();
            request.getSession().setAttribute("msg", "Error occurred while deleting product.");
            response.sendRedirect("admin-item?action=listProducts");
        }
    }
    
    private void unLockProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số id từ URL
            String idParam = request.getParameter("id");
            System.out.println("ID Param: " + idParam); // Debug để in ra giá trị id

            if (idParam == null || idParam.isEmpty()) {
                throw new IllegalArgumentException("Product ID is missing!");
            }

            int productID = Integer.parseInt(idParam);
            boolean flag = productDAO.unlockProduct(productID);

            String msg;
            if (flag) {
                msg = "Unlock successfully!";
            } else {
                msg = "Fail to unlock product!";
            }

            request.getSession().setAttribute("msg", msg);
            response.sendRedirect("admin-item?action=listProducts");

        } catch (NumberFormatException e) {
            // Nếu ID không hợp lệ (không phải là số)
            request.getSession().setAttribute("msg", "Invalid product ID.");
            response.sendRedirect("admin-item?action=listProducts");
        } catch (Exception e) {
            // Xử lý các lỗi khác
            e.printStackTrace();
            request.getSession().setAttribute("msg", "Error occurred while deleting product.");
            response.sendRedirect("admin-item?action=listProducts");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
