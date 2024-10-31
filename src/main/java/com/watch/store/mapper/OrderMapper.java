package com.watch.store.mapper;

import com.watch.store.dto.request.OrderRequestDTO;
import com.watch.store.dto.response.OrderResponseDTO;
import com.watch.store.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderDetailMapper.class, UserMapper.class})
public interface OrderMapper {
    Order orderRequestDTOToOrder(OrderRequestDTO orderRequestDTO);

    @Mapping(source = "user", target = "userResponseDTO")
    @Mapping(source = "orderDetails", target = "orderDetailResponseDTOS")
    OrderResponseDTO orderToOrderResponseDTO(Order order);
}
