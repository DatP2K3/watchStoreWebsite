package com.watch.store.repository;

import com.watch.store.model.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Integer> {
}
