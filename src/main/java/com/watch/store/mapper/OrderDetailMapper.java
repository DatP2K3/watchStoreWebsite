package com.watch.store.mapper;

import com.watch.store.dto.request.OrderDetailRequestDTO;
import com.watch.store.dto.response.OrderDetailResponseDTO;
import com.watch.store.model.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderDetailMapper {
    OrderDetail orderDetailRequestDTOToOrderDetail(OrderDetailRequestDTO orderDetailRequestDTO);
    OrderDetailResponseDTO orderDetailToOrderDetailResponseDTO(OrderDetail orderDetail);
}
