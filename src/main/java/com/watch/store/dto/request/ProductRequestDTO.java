package com.watch.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;

import java.util.Collection;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO {
    int id;
    @NotBlank(message = "Product name cannot be blank")
    String name;
    @NotBlank(message = "Description cannot be blank")
    String gender;
    @NotNull(message = "Description cannot be null")
    private Object details;
    @Positive(message = "Total Quantity must be positive")
    int totalQuantity;
    @Positive(message = "Original Price must be positive")
    int originalPrice;
    @NotBlank(message = "SKU cannot be blank")
    String sku;
    @NotNull(message = "Description cannot be null")
    Collection<String> productImages = new HashSet<>();
    @Positive(message = "Category ID must be positive")
    int seriesId;
}
