package com.watch.store.specification;

import com.watch.store.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> hasPriceBetween(int minPrice, int maxPrice) {
        return (root, query, criteriaBuilder) -> {
            Predicate greaterThanOrEqual = criteriaBuilder.greaterThanOrEqualTo(root.get("discountedPrice"), minPrice);
            Predicate lessThanOrEqual = criteriaBuilder.lessThanOrEqualTo(root.get("discountedPrice"), maxPrice);
            return criteriaBuilder.and(greaterThanOrEqual, lessThanOrEqual);
        };
    }
}
