/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authenticate;

import util.GoogleUtils;
import model.GoogleAccount;
import context.AccountDAO;
import context.ShopDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import util.PasswordUtil;

/**
 *
 * @author LENOVO
 */
public class LoginGoogleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginGoogleServlet() {
        super();
    }

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
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GoogleAccount googlePojo = GoogleUtils.getUserInfo(accessToken);

            HttpSession session = request.getSession();
            if (isAccountExist(googlePojo.getEmail())) {
                session.setAttribute("username", acc.getUserName());
                session.setAttribute("user", acc);
                if (acc.getRole() == 1) {
                    if (acc.isStatus() == false) {
                        request.setAttribute("message", "Your account has been temporarily banned by the admin. For more information, please contact support at hotline 0999-xxx-xxx");
                        request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
                    } else {
                        session.setAttribute("role", "customer");
                        session.setMaxInactiveInterval(10 * 24 * 60 * 60);
                        response.sendRedirect("/OrderingSystem");
                    }
                }
                if (acc.getRole() == 2) {
                    ShopDAO sDAO = new ShopDAO();
                    if (sDAO.getShopByID(acc.getShopID()).getStatus() == false) {
                        request.setAttribute("message", "Your restaurant registration is still pending approval from the admin.");
                        request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
                    } else {
                        session.setAttribute("role", "shop");
                        session.setMaxInactiveInterval(10 * 24 * 60 * 60);
                        response.sendRedirect("/OrderingSystem/restaurant-detail?shopId=" + acc.getShopID());
                    }
                }
            } else {

                request.setAttribute("username", googlePojo.getEmail().split("@")[0]);
                request.setAttribute("email", googlePojo.getEmail());
                request.setAttribute("name", googlePojo.getName());
                request.setAttribute("picture", googlePojo.getPicture());
                request.getRequestDispatcher("WEB-INF/view/set-password.jsp").forward(request, response);

            }

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

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String picture = request.getParameter("picture");
        String hashedPassword = PasswordUtil.hashPassword(password);
        Account toAdd = new Account(username, hashedPassword, name, null, email, null, picture, 1);
        accountDAO.createAccount(toAdd);
        session.setAttribute("username", toAdd.getUserName());
        session.setAttribute("user", toAdd);
        session.setMaxInactiveInterval(5 * 24 * 60 * 60);
        response.sendRedirect("/OrderingSystem");

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
    AccountDAO accountDAO = new AccountDAO();
    Account acc;

    protected boolean isAccountExist(String email) {

        acc = accountDAO.checkAccountByEmail(email);
        if (acc == null) {
            return false;
        } else {
            return true;
        }
    }

}
