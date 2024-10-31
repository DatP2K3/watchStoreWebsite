package com.watch.store.controller;

import com.watch.store.dto.request.ProductRequestDTO;
import com.watch.store.dto.response.ProductResponseDTO;
import com.watch.store.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("/products/{productID}")
    ResponseEntity<ProductResponseDTO> getProduct(@PathVariable int productID) {
        ProductResponseDTO productResponseDTO = productService.getProduct(productID);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("/products")
    ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOs = productService.getAllProducts();
        return ResponseEntity.ok(productResponseDTOs);
    }

    @GetMapping("/products/sorted-by-total-sales-desc")
    ResponseEntity<List<ProductResponseDTO>> getAllProductsSortedByTotalSalesDesc() {
        List<ProductResponseDTO> productResponseDTOs = productService.getAllProductsSortedByTotalSalesDesc();
        return ResponseEntity.ok(productResponseDTOs);
    }

    @GetMapping("/products/sorted-by-average-rate-desc")
    ResponseEntity<List<ProductResponseDTO>> getAllProductsSortedByAverageRateDesc() {
        List<ProductResponseDTO> productResponseDTOs = productService.getAllProductsSortedByAverageRateDesc();
        return ResponseEntity.ok(productResponseDTOs);
    }

    @GetMapping("/products/between-price/{minPrice}/{maxPrice}")
    ResponseEntity<List<ProductResponseDTO>> getAllProductsBetweenPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
        List<ProductResponseDTO> productResponseDTOs = productService.getAllProductsBetweenPrice(minPrice, maxPrice);
        return ResponseEntity.ok(productResponseDTOs);
    }

    @PutMapping("/products")
    ResponseEntity<ProductResponseDTO> updateProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.updateProduct(productRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("/products/series/{seriesID}")
    ResponseEntity<List<ProductResponseDTO>> getProductsBySeries(@PathVariable int seriesID) {
        List<ProductResponseDTO> productResponseDTOs = productService.getProductsBySeries(seriesID);
        return ResponseEntity.ok(productResponseDTOs);
    }

    @DeleteMapping("/products/{productID}")
    ResponseEntity<Void> deleteProduct(@PathVariable int productID) {
        productService.deleteProduct(productID);
        return ResponseEntity.ok().build();
    }
}
