package controller.authenticate;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import util.Email;

@WebServlet(name = "OtpVerifyServlet", urlPatterns = {"/otp"})
public class OtpVerifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/verifyOtp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account tempAccount = (Account) session.getAttribute("tempAccount");
        String inputOtp = request.getParameter("otp");

        if (tempAccount != null && tempAccount.getCode().equals(inputOtp)) {
            // OTP is valid
            request.setAttribute("message", "OTP verified successfully. Please register your account.");
            request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("WEB-INF/view/verifyOtp.jsp").forward(request, response);
        }
    }
}
