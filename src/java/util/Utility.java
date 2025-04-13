/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import context.OrderItemDAO;
import context.ProductDAO;
import context.ShopDAO;

public class Utility {
    public static String getShopAddressByOrderID(int orderID){
        OrderItemDAO oiDAO = new OrderItemDAO();
        ProductDAO pDAO = new ProductDAO();
        ShopDAO sDAO = new ShopDAO();
        return sDAO.getShopByID(pDAO.getProductByID(oiDAO.getOrderItemByOrderID(orderID).getFirst().getProductId()).getShopId()).getAddress();
    }
    
}
