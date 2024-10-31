package com.watch.store.repository;

import com.watch.store.model.Series;
import com.watch.store.model.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Integer> {
    List<Series> getSeriesByTrademark(Trademark trademark);
}
