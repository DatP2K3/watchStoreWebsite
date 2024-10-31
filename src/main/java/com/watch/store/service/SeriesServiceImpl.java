package com.watch.store.service;

import com.watch.store.dto.request.SeriesRequestDTO;
import com.watch.store.dto.response.SeriesResponseDTO;
import com.watch.store.exception.ResourceNotFoundException;
import com.watch.store.mapper.SeriesMapper;
import com.watch.store.model.Series;
import com.watch.store.model.Trademark;
import com.watch.store.repository.SeriesRepository;
import com.watch.store.repository.TrademarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;
    private final TrademarkRepository trademarkRepository;

    @Override
    public List<SeriesResponseDTO> getAllSeries() {
        List<Series> listSeries =  seriesRepository.findAll();
        List<SeriesResponseDTO> seriesResponseDTOS = new ArrayList<>();
        for (Series series : listSeries) {
            SeriesResponseDTO seriesResponseDTO = seriesMapper.seriesToSeriesResponseDTO(series);
            seriesResponseDTOS.add(seriesResponseDTO);
        }
        return seriesResponseDTOS;
    }

    @Override
    public SeriesResponseDTO getSeries(int SeriesId) {
        Series series = seriesRepository.findById(SeriesId)
                .orElseThrow(() -> new ResourceNotFoundException("Series not found with id: " + SeriesId));
        return seriesMapper.seriesToSeriesResponseDTO(series);
    }

    @Override
    public List<SeriesResponseDTO> getSeriesByTrademark(int trademarkID) {
        Trademark trademark = trademarkRepository.findById(trademarkID)
                .orElseThrow(() -> new ResourceNotFoundException("Trademark not found with id: " + trademarkID));
        List<Series> listSeries = seriesRepository.getSeriesByTrademark(trademark);
        List<SeriesResponseDTO> seriesResponseDTOS = new ArrayList<>();
        for (Series series : listSeries) {
            SeriesResponseDTO seriesResponseDTO = seriesMapper.seriesToSeriesResponseDTO(series);
            seriesResponseDTOS.add(seriesResponseDTO);
        }
        return seriesResponseDTOS;
    }

    @Override
    public SeriesResponseDTO createSeries(SeriesRequestDTO seriesRequestDTO) {
        Trademark trademark = trademarkRepository.findById(seriesRequestDTO.getTrademarkID())
                .orElseThrow(() -> new ResourceNotFoundException("Trademark not found with id: " + seriesRequestDTO.getTrademarkID()));
        Series series = seriesMapper.seriesRequestDTOToSeries(seriesRequestDTO);
        trademark.addSeries(series);
        return seriesMapper.seriesToSeriesResponseDTO(seriesRepository.save(series));
    }

    @Override
    public SeriesResponseDTO updateSeries(SeriesRequestDTO seriesRequestDTO) {
        Series series = seriesRepository.findById(seriesRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Series not found with id: " + seriesRequestDTO.getId()));
        seriesMapper.updateSeriesFromSeriesRequestDTO(seriesRequestDTO, series);
        return seriesMapper.seriesToSeriesResponseDTO(seriesRepository.save(series));
    }

    @Override
    public void deleteSeries(int SeriesId) {
        Series series = seriesRepository.findById(SeriesId)
                .orElseThrow(() -> new ResourceNotFoundException("Series not found with id: " + SeriesId));
        seriesRepository.delete(series);
    }
}
