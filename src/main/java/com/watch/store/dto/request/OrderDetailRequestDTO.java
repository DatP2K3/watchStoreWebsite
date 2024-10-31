package com.watch.store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailRequestDTO {
    int id;
    int price;
    int quantity;
    int totalPrice;
    int productID;
}
