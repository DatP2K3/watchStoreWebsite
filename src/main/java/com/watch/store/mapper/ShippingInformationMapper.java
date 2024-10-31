package com.watch.store.mapper;

import com.watch.store.dto.request.ShippingInformationRequestDTO;
import com.watch.store.model.ShippingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShippingInformationMapper {
    ShippingInformation shippingInformationRequestDTOToShippingInformation(ShippingInformationRequestDTO shippingInformationRequestDTO);
    @Mapping(target = "id", ignore = true)
    void updateShippingInformationFromShippingInformationRequestDTO(ShippingInformationRequestDTO shippingInformationRequestDTO, @MappingTarget ShippingInformation shippingInformation);
}
