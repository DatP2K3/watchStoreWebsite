package com.watch.store.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDiscountRequestDTO {
    int id;
    @Positive(message = "Product ID must be positive")
    int productID;
    @Positive(message = "Discount price must be positive")
    int discountPrice;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @FutureOrPresent(message = "Start date must be in the future or present")
    LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @FutureOrPresent(message = "End date must be in the future or present")
    LocalDateTime endDate;
    @NotBlank(message = "Discount name cannot be blank")
    String discountName;
    @Positive(message = "Priority must be positive")
    int priority;
}
