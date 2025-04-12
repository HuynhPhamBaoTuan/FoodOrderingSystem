package controller.authenticate;

import context.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import util.Email;
import util.PasswordUtil;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            register(request, response);
        } else if ("verifyOTP".equals(action)) {
            verifyOTP(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");

        // Validate email format
        if (!isValidEmail(email) ) {
            request.setAttribute("message", "Invalid email format.");
            request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
            return;
        }
        if (!isValidPassword(password)) {
            request.setAttribute("message", "Invalid password. Need to have both number and letter and more than 8 characters.");
            request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
            return;
        }

        // Generate OTP
        String otp = Email.getRandom();

        // Create account object without saving to DB yet
        Account tempAccount = new Account();
        tempAccount.setEmail(email);
        tempAccount.setCode(otp); // Store OTP for verification
        tempAccount.setFullName(fullName);
        tempAccount.setUserName(userName);
        tempAccount.setPassword(PasswordUtil.hashPassword(password));
        tempAccount.setPhoneNumber(phoneNumber);
        tempAccount.setAddress(address);
        tempAccount.setRole(1); // Assuming role is set to 1

        // Send OTP email
        if (Email.sendEmail(tempAccount)) {
            HttpSession session = request.getSession();
            session.setAttribute("tempAccount", tempAccount); // Store account temporarily
            request.setAttribute("message", "OTP sent to your email. Please verify.");
            request.getRequestDispatcher("WEB-INF/view/verifyOtp.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Failed to send OTP.");
            request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
        }
    }

    private void verifyOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account tempAccount = (Account) session.getAttribute("tempAccount");
        String inputOtp = request.getParameter("otp");

        if (tempAccount != null && tempAccount.getCode().equals(inputOtp)) {
            // OTP is correct, now save the account
            AccountDAO acDAO = new AccountDAO();
            if (acDAO.createAccount(tempAccount)) {
                session.removeAttribute("tempAccount"); // Clear temp account
                session.setAttribute("message", "Registered successfully! Login to continue.");
                response.sendRedirect("login");
            } else {
                request.setAttribute("message", "Failed to register account.");
                request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("WEB-INF/view/verifyOtp.jsp").forward(request, response);
        }
    }

    private boolean isValidEmail(String email) {
        // Basic email validation regex
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
    
    public static boolean isValidPassword(String password) {
        // Kiểm tra độ dài của mật khẩu
        if (password.length() < 8) {
            return false;
        }

        // Kiểm tra có ít nhất một chữ cái và một chữ số
        boolean hasLetter = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        // Nếu mật khẩu chứa cả chữ cái và số thì hợp lệ
        return hasLetter && hasDigit;
    }

    @Override
    public String getServletInfo() {
        return "Register Servlet";
    }
}
