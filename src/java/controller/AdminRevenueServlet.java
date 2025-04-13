/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import context.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import model.ShopRevenue;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@WebServlet(name = "AdminRevenueServlet", urlPatterns = {"/adminRevenue"})
public class AdminRevenueServlet extends HttpServlet {

    private OrderDAO orderDao = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                getListShopFinance(request, response);
                break;
            case "export":
                exportToExcel(request, response);
                break;
            default:
                getListShopFinance(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    // getList
    private void getListShopFinance(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ShopRevenue> shops = orderDao.getRevenueByShop();
        request.setAttribute("shops", shops);
        request.getRequestDispatcher("WEB-INF/view/admin-revenue.jsp").forward(request, response);
    }

    // Export CSV file
    private static String getStringCellValue(Cell cell) {
        return cell != null ? cell.getStringCellValue() : "";
    }

    private static int getNumericCellValue(Cell cell) {
        return cell != null ? (int) cell.getNumericCellValue() : 0;
    }

    private void exportToExcel(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    boolean flag = false; // Default to false
    List<ShopRevenue> shops = orderDao.getRevenueByShop(); // Load shop data
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select directory to save");
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int userSelection = fileChooser.showSaveDialog(null);
    if (userSelection != JFileChooser.APPROVE_OPTION) {
        // User cancelled the selection, exit the function with shops data intact
        request.setAttribute("shops", shops); // Set the data to retain it
        request.getSession().setAttribute("msg", "Export cancelled!");
        request.getRequestDispatcher("WEB-INF/view/admin-revenue.jsp").forward(request, response);
        return;
    }

    File directionToSave = fileChooser.getSelectedFile();
    if (directionToSave == null) {
        // No directory selected, exit the function with shops data intact
        request.setAttribute("shops", shops); // Set the data to retain it
        request.getSession().setAttribute("msg", "No directory selected for export!");
        request.getRequestDispatcher("WEB-INF/view/admin-revenue.jsp").forward(request, response);
        return;
    }

    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Shop Revenue Data");
    String[] headerTitle = {"ShopID", "Shop Name", "Shop Owner", "VNPay Revenue", "COD Revenue", "Total Revenue"};
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < headerTitle.length; i++) {
        headerRow.createCell(i).setCellValue(headerTitle[i]);
    }

    int rowNum = 1;
    for (ShopRevenue shop : shops) {
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(shop.getShopID());
        row.createCell(1).setCellValue(shop.getShopName());
        row.createCell(2).setCellValue(shop.getShopOwner());
        row.createCell(3).setCellValue(shop.getVnPayRevenue());
        row.createCell(4).setCellValue(shop.getCodRevenue());
        row.createCell(5).setCellValue(shop.getTotalRevenue());
        rowNum++;
    }

    String fileName = "shopRevenueData.xlsx";
    File excelFile = new File(directionToSave, fileName);

    try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
        workbook.write(fileOut);
        System.out.println("Success export");
        flag = true; // Set to true only if the export was successful
    } catch (IOException e) {
        System.out.println("Error during export: " + e.getMessage());
        flag = false; // Keep flag false if there's an error
    } finally {
        workbook.close(); // Close workbook to free up resources
    }

    // Ensure shops data is always set in the request
    request.setAttribute("shops", shops);
    request.getSession().setAttribute("msg", flag ? "Export success!" : "Export fail!");
    request.getRequestDispatcher("WEB-INF/view/admin-revenue.jsp").forward(request, response);
}


}
