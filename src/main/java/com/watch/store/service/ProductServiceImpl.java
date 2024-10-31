package com.watch.store.service;

import com.watch.store.dto.request.ProductRequestDTO;
import com.watch.store.dto.response.ProductResponseDTO;
import com.watch.store.exception.ResourceNotFoundException;
import com.watch.store.mapper.ProductMapper;
import com.watch.store.model.Product;
import com.watch.store.model.Series;
import com.watch.store.repository.ProductRepository;
import com.watch.store.repository.SeriesRepository;
import com.watch.store.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SeriesRepository seriesRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Series series = seriesRepository.findById(productRequestDTO.getSeriesId())
                .orElseThrow(() -> new ResourceNotFoundException("Series not found with id: " + productRequestDTO.getSeriesId()));
        Product product = productMapper.productRequestDTOToProduct(productRequestDTO);
        product.setSeries(series);
        return productMapper.productToProductResponseDTO(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO getProduct(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(productMapper::productToProductResponseDTO).orElse(null);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : products) {
            productResponseDTOS.add(productMapper.productToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }

    @Override
    public List<ProductResponseDTO> getAllProductsBetweenPrice(int minPrice, int maxPrice) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice > 0 && maxPrice > 0 && minPrice < maxPrice) {
            spec = spec.and(ProductSpecification.hasPriceBetween(minPrice, maxPrice));
        }
        List<Product> products = productRepository.findAll(spec);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : products) {
            productResponseDTOS.add(productMapper.productToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }

    @Override
    public List<ProductResponseDTO> getAllProductsSortedByTotalSalesDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC, "totalSales");
        List<Product> products = productRepository.findAll(sort);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : products) {
            productResponseDTOS.add(productMapper.productToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }

    @Override
    public List<ProductResponseDTO> getAllProductsSortedByAverageRateDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC, "averageRate");
        List<Product> products = productRepository.findAll(sort);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : products) {
            productResponseDTOS.add(productMapper.productToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productRequestDTO.getId()));
        productMapper.updateProductFromProductRequestDTO(productRequestDTO, product);
        return productMapper.productToProductResponseDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.findById(productId)
                .ifPresent(productRepository::delete);
    }

    @Override
    public List<ProductResponseDTO> getProductsBySeries(int seriesId) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new ResourceNotFoundException("Series not found with id: " + seriesId));
        List<Product> products = productRepository.getProductsBySeries(series);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : products) {
            productResponseDTOS.add(productMapper.productToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }
}
