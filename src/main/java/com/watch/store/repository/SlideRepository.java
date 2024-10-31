package com.watch.store.repository;

import com.watch.store.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlideRepository extends JpaRepository<Slide, Integer> {
}
