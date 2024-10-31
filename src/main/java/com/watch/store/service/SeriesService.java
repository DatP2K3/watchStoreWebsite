package com.watch.store.service;

import com.watch.store.dto.request.SeriesRequestDTO;
import com.watch.store.dto.response.SeriesResponseDTO;

import java.util.List;

public interface SeriesService {
    List<SeriesResponseDTO> getAllSeries();

    SeriesResponseDTO getSeries(int SeriesId);

    List<SeriesResponseDTO> getSeriesByTrademark(int trademarkID);

    SeriesResponseDTO createSeries(SeriesRequestDTO SeriesRequestDTO);

    SeriesResponseDTO updateSeries(SeriesRequestDTO SeriesRequestDTO);

    void deleteSeries(int SeriesId);
}
