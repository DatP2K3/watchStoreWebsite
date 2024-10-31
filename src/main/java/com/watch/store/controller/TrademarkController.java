package com.watch.store.controller;

import com.watch.store.dto.request.TrademarkRequestDTO;
import com.watch.store.dto.response.TrademarkResponseDTO;
import com.watch.store.service.TrademarkService;
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
@CrossOrigin(origins = "http://localhost:5173")
public class TrademarkController {
    private final TrademarkService trademarkService;

    @PostMapping("/trademarks")
    ResponseEntity<TrademarkResponseDTO> createTrademark(@Valid @RequestBody TrademarkRequestDTO trademarkRequestDTO) {
        TrademarkResponseDTO trademarkResponseDTO = trademarkService.createTrademark(trademarkRequestDTO);
        return ResponseEntity.ok(trademarkResponseDTO);
    }

    @GetMapping("/trademarks")
    ResponseEntity<List<TrademarkResponseDTO>> getAllTrademarks() {
        List<TrademarkResponseDTO> trademarkResponseDTOS = trademarkService.getAllTrademarks();
        return ResponseEntity.ok(trademarkResponseDTOS);
    }

    @GetMapping("/trademarks/{trademarkID}")
    ResponseEntity<TrademarkResponseDTO> getTrademark(@PathVariable int trademarkID) {
        TrademarkResponseDTO trademarkResponseDTO = trademarkService.getTrademark(trademarkID);
        return ResponseEntity.ok(trademarkResponseDTO);
    }

    @PutMapping("/trademarks")
    ResponseEntity<TrademarkResponseDTO> updateTrademark(@Valid @RequestBody TrademarkRequestDTO trademarkRequestDTO) {
        TrademarkResponseDTO trademarkResponseDTO = trademarkService.updateTrademark(trademarkRequestDTO);
        return ResponseEntity.ok(trademarkResponseDTO);
    }

    @DeleteMapping("/trademarks/{trademarkID}")
    ResponseEntity<Void> deleteTrademark(@PathVariable int trademarkID) {
        trademarkService.deleteTrademark(trademarkID);
        return ResponseEntity.noContent().build();
    }
}
