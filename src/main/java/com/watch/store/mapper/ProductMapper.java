package com.watch.store.mapper;

import com.watch.store.dto.request.ProductRequestDTO;
import com.watch.store.dto.response.ProductResponseDTO;
import com.watch.store.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product productRequestDTOToProduct(ProductRequestDTO productRequestDTO);

    @Mapping(source = "series.id", target = "seriesID")
    @Mapping(source = "productDiscounts", target = "productDiscountResponseDTOS")
    ProductResponseDTO productToProductResponseDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalSales", ignore = true)
    @Mapping(target = "averageRate", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "productDiscounts", ignore = true)
    void updateProductFromProductRequestDTO(ProductRequestDTO productRequestDTO, @MappingTarget Product product);
}
