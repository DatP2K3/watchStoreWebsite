package com.watch.store.service;

import com.watch.store.dto.request.OrderRequestDTO;
import com.watch.store.dto.response.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO getOrderById(int orderID);
    List<OrderResponseDTO> getAllOrders();
    List<OrderResponseDTO> getOrderByUserID(int userID);
    void deleteOrder(int orderID);
    OrderResponseDTO acceptOrder(int orderID);
}
