package com.watch.store.model;

import com.watch.store.converter.StringConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "gender")
    String gender;

    @Lob
    @Convert(converter = StringConverter.class)
    @Column(name = "description", columnDefinition = "TEXT")
    private Object details;

    @Lob
    @Convert(converter = StringConverter.class)
    @Column(name = "product_images", columnDefinition = "TEXT")
    private Collection<String> productImages = new HashSet<>();

    @Column(name = "total_quantity")
    int totalQuantity;

    @Column(name = "original_price")
    int originalPrice;

    @Column(name = "discounted_price")
    int discountedPrice;

    @Column(name = "total_sales")
    int totalSales = 0;

    @Column(name = "average_rate")
    double averageRate = 0;

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Collection<Comment> comments;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL)
    Collection<ProductDiscount> productDiscounts = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="series_id")
    Series series;

    @Column(name = "sku")
    String sku;

    public void addProductDiscount(ProductDiscount productDiscount){
        if (productDiscounts == null){
            productDiscounts = new HashSet<>();
        }
        productDiscounts.add(productDiscount);
    }

    public void addComment(Comment comment){
        if (comments == null){
            comments = new ArrayList<>();
        }
        comments.add(comment);
        comment.setProduct(this);
    }

    @PrePersist
    public void prePersist() {
        this.discountedPrice = this.originalPrice;
    }
}
