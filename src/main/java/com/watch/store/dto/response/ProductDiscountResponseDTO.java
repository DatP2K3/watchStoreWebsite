package com.watch.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDiscountResponseDTO {
    int id;
    int discountPrice;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String discountName;
    int priority;
    ProductResponseDTO productResponseDTO;
}
