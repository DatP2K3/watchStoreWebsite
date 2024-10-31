package com.watch.store.service;

import com.watch.store.dto.request.ProductDiscountRequestDTO;
import com.watch.store.dto.response.ProductDiscountResponseDTO;

import java.util.List;

public interface ProductDiscountService {
    ProductDiscountResponseDTO getProductDiscount(int productId);
    List<ProductDiscountResponseDTO> getAllProductDiscounts();
    ProductDiscountResponseDTO createProductDiscount(ProductDiscountRequestDTO productDiscountRequestDTO);
    ProductDiscountResponseDTO updateProductDiscount(ProductDiscountRequestDTO productDiscountRequestDTO);
    void deleteProductDiscount(int productDiscountId);
}
