package com.watch.store.controller;

import com.watch.store.dto.request.OrderRequestDTO;
import com.watch.store.dto.response.OrderResponseDTO;
import com.watch.store.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @GetMapping("/orders/{orderID}")
    ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable int orderID) {
        OrderResponseDTO orderResponseDTO = orderService.getOrderById(orderID);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @GetMapping("/orders/user/{userID}")
    ResponseEntity<List<OrderResponseDTO>> getOrderByUserId(@PathVariable int userID) {
        List<OrderResponseDTO> orderResponseDTOs = orderService.getOrderByUserID(userID);
        return ResponseEntity.ok(orderResponseDTOs);
    }

    @GetMapping("/orders")
    ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orderResponseDTOs = orderService.getAllOrders();
        return ResponseEntity.ok(orderResponseDTOs);
    }

    @DeleteMapping("/orders/{orderID}")
    ResponseEntity<Void> deleteOrder(@PathVariable int orderID) {
        orderService.deleteOrder(orderID);
        return ResponseEntity.ok().build();
    }

    @PutMapping("orders/{orderID}")
    ResponseEntity<OrderResponseDTO> acceptOrder(@PathVariable int orderID) {
        OrderResponseDTO orderResponseDTO = orderService.acceptOrder(orderID);
        return ResponseEntity.ok(orderResponseDTO);
    }
}
