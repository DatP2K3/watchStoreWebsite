package com.watch.store.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="shipping_informations")
public class ShippingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "phone")
    String phone;

    @Column(name = "city")
    String city;

    @Column(name = "district")
    String district;

    @Column(name = "ward")
    String ward;

    @Column(name = "detailed_address")
    String detailedAddress;
}