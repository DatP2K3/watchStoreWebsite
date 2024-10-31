package com.watch.store.service;

import com.watch.store.dto.request.ProductRequestDTO;
import com.watch.store.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO getProduct(int productId);
    List<ProductResponseDTO> getAllProducts();
    List<ProductResponseDTO> getAllProductsBetweenPrice(int minPrice, int maxPrice);
    List<ProductResponseDTO> getAllProductsSortedByTotalSalesDesc();
    List<ProductResponseDTO> getAllProductsSortedByAverageRateDesc();
    ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO);
    void deleteProduct(int productId);
    List<ProductResponseDTO> getProductsBySeries(int seriesId);
}
