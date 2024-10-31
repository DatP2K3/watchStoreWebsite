package com.watch.store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDTO {
    int id;
    String note;
    String status = "Chờ xác nhận";
    String paymentMethod;
    int userID;
    Collection<OrderDetailRequestDTO> orderDetailRequestDTOS = new HashSet<>();
}
