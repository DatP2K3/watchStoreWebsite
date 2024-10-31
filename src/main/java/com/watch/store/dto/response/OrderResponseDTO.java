package com.watch.store.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDTO {
    int id;
    int totalPrice;
    int totalQuantity;
    String note;
    String status;
    String paymentMethod;
    UserResponseDTO userResponseDTO;
    Collection<OrderDetailResponseDTO> orderDetailResponseDTOS = new HashSet<>();
}
