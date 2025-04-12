/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public class OrderHistoryDTO {
    private Order order;
    private List<OrderItemHistoryDTO> orderItem;

    public OrderHistoryDTO() {
    }

    public OrderHistoryDTO(Order order, List<OrderItemHistoryDTO> orderItem) {
        this.order = order;
        this.orderItem = orderItem;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItemHistoryDTO> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItemHistoryDTO> orderItem) {
        this.orderItem = orderItem;
    }
    
    
}