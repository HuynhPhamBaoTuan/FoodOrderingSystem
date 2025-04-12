package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import context.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orderRevenueData")
public class OrderRevenueDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO = new OrderDAO();      

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> data = orderDAO.getOrderData();
       
        Gson gson = new Gson();
        String jsonData = gson.toJson(data);
        response.getWriter().write(jsonData);
    }
}
