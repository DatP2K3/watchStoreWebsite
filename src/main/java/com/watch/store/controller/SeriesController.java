package com.watch.store.controller;

import com.watch.store.dto.request.SeriesRequestDTO;
import com.watch.store.dto.response.SeriesResponseDTO;
import com.watch.store.model.Series;
import com.watch.store.service.SeriesService;
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
public class SeriesController {
    private final SeriesService seriesService;

    @PostMapping("/series")
    ResponseEntity<SeriesResponseDTO> createSeries(@Valid @RequestBody SeriesRequestDTO seriesRequestDTO) {
        SeriesResponseDTO seriesResponseDTO = seriesService.createSeries(seriesRequestDTO);
        return ResponseEntity.ok(seriesResponseDTO);
    }

    @GetMapping("/series")
    ResponseEntity<List<SeriesResponseDTO>> getAllSeries() {
        List<SeriesResponseDTO> seriesResponseDTOS = seriesService.getAllSeries();
        return ResponseEntity.ok(seriesResponseDTOS);
    }

    @GetMapping("/series/{seriesID}")
    ResponseEntity<SeriesResponseDTO> getSeries(@PathVariable int seriesID) {
        SeriesResponseDTO seriesResponseDTO = seriesService.getSeries(seriesID);
        return ResponseEntity.ok(seriesResponseDTO);
    }

    @GetMapping("/series/trademark/{trademarkID}")
    ResponseEntity<List<SeriesResponseDTO>> getListSeriesByTradeMarkID(@PathVariable int trademarkID) {
        List<SeriesResponseDTO> seriesResponseDTOs = seriesService.getSeriesByTrademark(trademarkID);
        return ResponseEntity.ok(seriesResponseDTOs);
    }

    @PutMapping("/series")
    ResponseEntity<SeriesResponseDTO> updateSeries(@Valid @RequestBody SeriesRequestDTO seriesRequestDTO) {
        SeriesResponseDTO seriesResponseDTO = seriesService.updateSeries(seriesRequestDTO);
        return ResponseEntity.ok(seriesResponseDTO);
    }

    @DeleteMapping("/series/{seriesID}")
    ResponseEntity<Void> deleteSeries(@PathVariable int seriesID) {
        seriesService.deleteSeries(seriesID);
        return ResponseEntity.noContent().build();
    }
}
