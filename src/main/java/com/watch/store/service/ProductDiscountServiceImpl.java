package com.watch.store.service;

import com.watch.store.dto.request.ProductDiscountRequestDTO;
import com.watch.store.dto.response.ProductDiscountResponseDTO;
import com.watch.store.exception.ResourceNotFoundException;
import com.watch.store.mapper.ProductDiscountMapper;
import com.watch.store.model.Product;
import com.watch.store.model.ProductDiscount;
import com.watch.store.repository.ProductDiscountRepository;
import com.watch.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDiscountServiceImpl implements ProductDiscountService{
    private final ProductDiscountRepository productDiscountRepository;
    private final ProductDiscountMapper productDiscountMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductDiscountResponseDTO getProductDiscount(int productId) {
        Optional<ProductDiscount> productDiscount = productDiscountRepository.findById(productId);
        return productDiscount.map(productDiscountMapper::productDiscountToProductDiscountResponseSTO).orElse(null);
    }

    @Override
    public List<ProductDiscountResponseDTO> getAllProductDiscounts() {
        List<ProductDiscount> productDiscounts = productDiscountRepository.findAll();
        List<ProductDiscountResponseDTO> productDiscountResponseDTOS = new ArrayList<>();
        for (ProductDiscount productDiscount : productDiscounts) {
            productDiscountResponseDTOS.add(productDiscountMapper.productDiscountToProductDiscountResponseSTO(productDiscount));
        }
        return productDiscountResponseDTOS;
    }

    @Override
    public ProductDiscountResponseDTO createProductDiscount(ProductDiscountRequestDTO productDiscountRequestDTO) {
        ProductDiscount productDiscount = productDiscountMapper.productDiscountRequestDTOToProductDiscount(productDiscountRequestDTO);
        Product product = productRepository.findById(productDiscountRequestDTO.getProductID())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productDiscountRequestDTO.getProductID()));
        productDiscount.setProduct(product);
        ProductDiscount newProductDiscount = productDiscountRepository.save(productDiscount);
        product.addProductDiscount(newProductDiscount);
        return productDiscountMapper.productDiscountToProductDiscountResponseSTO(newProductDiscount);
    }

    @Override
    public ProductDiscountResponseDTO updateProductDiscount(ProductDiscountRequestDTO productDiscountRequestDTO) {
        ProductDiscount productDiscount = productDiscountRepository.findById(productDiscountRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product Discount not found with id: " + productDiscountRequestDTO.getId()));
        productDiscountMapper.updateProductDiscountFromProductDiscountRequestDTO(productDiscountRequestDTO, productDiscount);
        ProductDiscount newProductDiscount = productDiscountRepository.save(productDiscount);
        return productDiscountMapper.productDiscountToProductDiscountResponseSTO(newProductDiscount);
    }

    @Override
    public void deleteProductDiscount(int productDiscountId) {
        ProductDiscount productDiscount = productDiscountRepository.findById(productDiscountId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Discount not found with id: " + productDiscountId));
        productDiscountRepository.delete(productDiscount);
    }
}
