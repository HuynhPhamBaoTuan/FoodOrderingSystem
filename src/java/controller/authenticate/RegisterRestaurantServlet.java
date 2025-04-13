/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authenticate;

import context.AccountDAO;
import context.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import model.Account;
import model.Shop;
import util.Email;
import util.PasswordUtil;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

@WebServlet(name = "RegisterRestaurantServlet", urlPatterns = {"/register-restaurant"})
public class RegisterRestaurantServlet extends HttpServlet {

    private static final String SAVE_DIR1 = "restaurantImages";
    private ShopDAO shopDAO = new ShopDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/register-restaurant.jsp").forward(request, response);
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

        Shop shop = null;

        String restaurantName = request.getParameter("restaurant-name");
        String description = request.getParameter("description");
        String addressRestaurant = request.getParameter("address");
        String timeOpenStr = request.getParameter("time-open");
        String timeCloseStr = request.getParameter("time-close");

        // Chuyển chuỗi time từ from thành LocalTime
        LocalTime timeOpen = null;
        LocalTime timeClose = null;

        try {
            timeOpen = LocalTime.parse(timeOpenStr);
            timeClose = LocalTime.parse(timeCloseStr);
        } catch (DateTimeParseException e) {
            request.setAttribute("message", "Invalid time format");
            request.getRequestDispatcher("WEB-INF/view/register-restaurant.jsp").forward(request, response);
            return;
        }

        System.out.println(restaurantName);
        System.out.println(description);
        System.out.println(addressRestaurant);

        // image
        String appPath = request.getServletContext().getRealPath("").replace("build\\web", "web");
        String savePath = appPath + File.separator + SAVE_DIR1;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String relativePath;
        if (filePart != null && filePart.getSize() > 0) {
            String filePath = savePath + File.separator + fileName;
            filePart.write(filePath);
            relativePath = "./" + SAVE_DIR1 + "/" + fileName;
            shop = new Shop(restaurantName, description, Boolean.FALSE, relativePath, addressRestaurant, timeOpen, timeClose);
        }

        int shopID = shopDAO.registerShop(shop);
        System.out.println(shopID);

        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");

        // Validate email format
        if (!isValidEmail(email)) {
            request.setAttribute("message", "Invalid email format.");
            request.getRequestDispatcher("WEB-INF/view/register-restaurant.jsp").forward(request, response);
            return;
        }
        if (!isValidPassword(password)) {
            request.setAttribute("message", "Invalid password. Need to have both number and letter and more than 8 characters.");
            request.getRequestDispatcher("WEB-INF/view/register-restaurant.jsp").forward(request, response);
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
        tempAccount.setShopID(shopID);
        tempAccount.setRole(2); // Assuming role is set to 2

        // Send OTP email
        if (Email.sendEmail(tempAccount)) {
            HttpSession session = request.getSession();
            session.setAttribute("tempAccount", tempAccount); // Store account temporarily
            request.setAttribute("message", "OTP sent to your email. Please verify.");
            request.getRequestDispatcher("WEB-INF/view/verify-restaurantOtp.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Failed to send OTP.");
            request.getRequestDispatcher("WEB-INF/view/register-restaurant.jsp").forward(request, response);
        }
    }

    private void verifyOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account tempAccount = (Account) session.getAttribute("tempAccount");
        String inputOtp = request.getParameter("otp");
        System.out.println("Temp Account hiện tại là: " + tempAccount.toString());
        System.out.println("Out hiện tại là" + inputOtp);
        System.out.println("==================================");
        System.out.println("UserName: " + tempAccount.getUserName());
        System.out.println("Pass: " + tempAccount.getPassword());
        System.out.println("FullName: " + tempAccount.getFullName());
        System.out.println("PhoneNumber: " + tempAccount.getPhoneNumber());
        System.out.println("Email: " + tempAccount.getEmail());
        System.out.println("Address: " + tempAccount.getAddress());
        System.out.println("Role: " + tempAccount.getRole());
        System.out.println("ShopID: " + tempAccount.getShopID());

        if (tempAccount != null && tempAccount.getCode().equals(inputOtp)) {
            // OTP is correct, now save the account
            AccountDAO acDAO = new AccountDAO();
            if (acDAO.createShopAccount(tempAccount)) {
                session.removeAttribute("tempAccount"); // Clear temp account
                //session.setAttribute("message", "Registered successfully! Login to continue.");
                request.getRequestDispatcher("WEB-INF/view/register-restaurant-announce.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Failed to register account.");
                request.getRequestDispatcher("WEB-INF/view/register-restaurant.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("WEB-INF/view/verify-restaurantOtp.jsp").forward(request, response);
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
