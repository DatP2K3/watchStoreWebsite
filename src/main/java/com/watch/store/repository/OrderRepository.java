package com.watch.store.repository;

import com.watch.store.model.Order;
import com.watch.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> getOrdersByUser(User user);
}
