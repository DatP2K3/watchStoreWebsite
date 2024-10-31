package com.watch.store.service;

import com.watch.store.dto.request.OrderDetailRequestDTO;
import com.watch.store.dto.request.OrderRequestDTO;
import com.watch.store.dto.response.OrderResponseDTO;
import com.watch.store.exception.ResourceNotFoundException;
import com.watch.store.mapper.OrderDetailMapper;
import com.watch.store.mapper.OrderMapper;
import com.watch.store.model.Order;
import com.watch.store.model.OrderDetail;
import com.watch.store.model.Product;
import com.watch.store.model.User;
import com.watch.store.repository.OrderRepository;
import com.watch.store.repository.ProductRepository;
import com.watch.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final ProductRepository productRepository;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = orderMapper.orderRequestDTOToOrder(orderRequestDTO);
        int totalPrice = 0;
        int totalQuantity = 0;
        List<OrderDetailRequestDTO> orderDetailRequestDTOS = new ArrayList<>(orderRequestDTO.getOrderDetailRequestDTOS());
        for (OrderDetailRequestDTO orderDetailRequestDTO : orderDetailRequestDTOS) {
            Product product = productRepository.findById(orderDetailRequestDTO.getProductID())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderDetailRequestDTO.getProductID()));
            OrderDetail orderDetail = orderDetailMapper.orderDetailRequestDTOToOrderDetail(orderDetailRequestDTO);
            orderDetail.setProduct(product);
            order.addOrderDetail(orderDetail);
            totalPrice += orderDetail.getPrice() * orderDetail.getQuantity();
            totalQuantity += orderDetail.getQuantity();
        }
        order.setTotalPrice(totalPrice);
        order.setTotalQuantity(totalQuantity);
        User user = userRepository.findById(orderRequestDTO.getUserID())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + orderRequestDTO.getUserID()));
        user.addOrder(order);
        OrderResponseDTO orderResponseDTO = orderMapper.orderToOrderResponseDTO(orderRepository.save(order));
        userRepository.save(user);
        return orderResponseDTO;
    }

    @Override
    public OrderResponseDTO getOrderById(int orderID) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderID));
        return orderMapper.orderToOrderResponseDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderResponseDTOS.add(orderMapper.orderToOrderResponseDTO(order));
        }
        return orderResponseDTOS;
    }

    @Override
    public List<OrderResponseDTO> getOrderByUserID(int userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        List<Order> orders = orderRepository.getOrdersByUser(user);
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderResponseDTOS.add(orderMapper.orderToOrderResponseDTO(order));
        }
        return orderResponseDTOS;
    }

    @Override
    public void deleteOrder(int orderID) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderID));
        orderRepository.delete(order);
    }

    @Override
    public OrderResponseDTO acceptOrder(int orderID) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderID));
        order.setStatus("Đang giao hàng");
        orderRepository.save(order);
        return orderMapper.orderToOrderResponseDTO(orderRepository.save(order));
    }
}
