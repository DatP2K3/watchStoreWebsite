package com.watch.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.engine.internal.Cascade;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =" id")
    int id;

    @Column(name = "rate")
    int rate;

    @Column(name = "comment", columnDefinition = "TEXT")
    String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    Product product;
}
