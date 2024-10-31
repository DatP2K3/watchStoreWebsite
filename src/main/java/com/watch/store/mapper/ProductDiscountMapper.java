package com.watch.store.mapper;

import com.watch.store.dto.request.ProductDiscountRequestDTO;
import com.watch.store.dto.response.ProductDiscountResponseDTO;
import com.watch.store.model.ProductDiscount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface ProductDiscountMapper {
    ProductDiscount productDiscountRequestDTOToProductDiscount(ProductDiscountRequestDTO productDiscountRequestDTO);

    @Mapping(source = "product", target = "productResponseDTO")
    ProductDiscountResponseDTO productDiscountToProductDiscountResponseSTO(ProductDiscount productDiscount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    void updateProductDiscountFromProductDiscountRequestDTO(ProductDiscountRequestDTO productDiscountRequestDTO, @MappingTarget ProductDiscount productDiscount);
}
