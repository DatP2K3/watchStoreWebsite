package com.watch.store.controller;

import com.watch.store.dto.request.ProductDiscountRequestDTO;
import com.watch.store.dto.response.ProductDiscountResponseDTO;
import com.watch.store.service.ProductDiscountService;
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
public class ProductDiscountController {
    private final ProductDiscountService productDiscountService;

    @GetMapping("/product-discounts/{productID}")
    ResponseEntity<ProductDiscountResponseDTO> getProductDiscount(@PathVariable int productID) {
        ProductDiscountResponseDTO productDiscountResponseDTO = productDiscountService.getProductDiscount(productID);
        return ResponseEntity.ok(productDiscountResponseDTO);
    }

    @GetMapping("/product-discounts")
    ResponseEntity<List<ProductDiscountResponseDTO>> getAllProductDiscounts() {
        List<ProductDiscountResponseDTO> productDiscountResponseDTOs = productDiscountService.getAllProductDiscounts();
        return ResponseEntity.ok(productDiscountResponseDTOs);
    }

    @PostMapping("/product-discounts")
    ResponseEntity<ProductDiscountResponseDTO> createProductDiscount(@Valid @RequestBody ProductDiscountRequestDTO productDiscountRequestDTO) {
        ProductDiscountResponseDTO productDiscountResponseDTO = productDiscountService.createProductDiscount(productDiscountRequestDTO);
        return ResponseEntity.ok(productDiscountResponseDTO);
    }

    @PutMapping("/product-discounts")
    ResponseEntity<ProductDiscountResponseDTO> updateProductDiscount(@Valid @RequestBody ProductDiscountRequestDTO productDiscountRequestDTO) {
        ProductDiscountResponseDTO productDiscountResponseDTO = productDiscountService.updateProductDiscount(productDiscountRequestDTO);
        return ResponseEntity.ok(productDiscountResponseDTO);
    }

    @DeleteMapping("/product-discounts/{productID}")
    ResponseEntity<Void> deleteProductDiscount(@PathVariable int productID) {
        productDiscountService.deleteProductDiscount(productID);
        return ResponseEntity.ok().build();
    }
}
