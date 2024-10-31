package com.watch.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "total_price")
    int totalPrice;

    @Column(name = "total_quantity")
    int totalQuantity;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    @Column(name = "status")
    String status;

    @Column(name = "payment_method")
    String paymentMethod;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    Collection<OrderDetail> orderDetails = new HashSet<>();

    public void addOrderDetail(OrderDetail orderDetail){
        if (orderDetails == null){
            orderDetails = new HashSet<>();
        }
        orderDetails.add(orderDetail);
    }
}
