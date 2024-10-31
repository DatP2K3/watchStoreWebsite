package com.watch.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponseDTO {
    int id;
    int price;
    int quantity;
    int totalPrice;
    ProductResponseDTO product;
}
