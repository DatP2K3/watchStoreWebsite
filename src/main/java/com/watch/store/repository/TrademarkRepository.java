package com.watch.store.repository;

import com.watch.store.model.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrademarkRepository extends JpaRepository<Trademark, Integer> {
}
