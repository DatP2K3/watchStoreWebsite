package com.watch.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "email", nullable=false)
    String email;

    @Column(name = "password", nullable=false)
    String password;

    @Column(name = "avatar")
    String avatar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_information_id")
    ShippingInformation shippingInformation;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH})
    Role role;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    Collection<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    Collection<Order> orders = new HashSet<>();

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new HashSet<>();
        }
        comments.add(comment);
        comment.setUser(this);
    }

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new HashSet<>();
        }
        orders.add(order);
        order.setUser(this);
    }
}
