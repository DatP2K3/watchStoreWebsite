package com.watch.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDTO {
    int id;
    String name;
    String gender;
    private Object details;
    int totalQuantity;
    int originalPrice;
    int discountedPrice;
    int totalSales;
    double averageRate;
    String sku;
    Collection<ProductDiscountResponseDTO> productDiscountResponseDTOS = new HashSet<>();
    Collection<String> productImages = new HashSet<>();
    int seriesID;
}
