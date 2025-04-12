
/**
 *
 * @author LENOVO
 */
import context.AccountDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import context.DBContext; 
import jakarta.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;

@WebServlet(name = "UserBanServlet", urlPatterns = {"/userBan"})
public class UserBanServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userID");
        String action = request.getParameter("action"); 

        AccountDAO accountDAO = new AccountDAO();
        
        if ("ban".equals(action)) {
            accountDAO.updateUserStatus(userName, 0);
        } else if ("unban".equals(action)) {
            accountDAO.updateUserStatus(userName, 1);
        }
        
        response.sendRedirect("userBan");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

       

        AccountDAO userDAO = new AccountDAO();
        List<Account> userList = userDAO.getAccountList();
        request.setAttribute("userList", userList);

        
        
        request.getRequestDispatcher("WEB-INF/view/userBan.jsp").forward(request, response);
    }
    
}   
