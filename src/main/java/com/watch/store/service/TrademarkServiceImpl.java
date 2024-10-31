package com.watch.store.service;

import com.watch.store.dto.request.TrademarkRequestDTO;
import com.watch.store.dto.response.TrademarkResponseDTO;
import com.watch.store.exception.ResourceNotFoundException;
import com.watch.store.mapper.TrademarkMapper;
import com.watch.store.model.Trademark;
import com.watch.store.repository.TrademarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrademarkServiceImpl implements TrademarkService{
    private final TrademarkRepository trademarkRepository;
    private final TrademarkMapper trademarkMapper;
    @Override
    public TrademarkResponseDTO createTrademark(TrademarkRequestDTO trademarkRequestDTO) {
        Trademark trademark = trademarkMapper.trademarkRequestDTOToTrademark(trademarkRequestDTO);
        return trademarkMapper.trademarkToTrademarkResponseDTO(trademarkRepository.save(trademark));
    }

    @Override
    public TrademarkResponseDTO getTrademark(int trademarkId) {
        Trademark trademark =  trademarkRepository.findById(trademarkId)
                .orElseThrow(() -> new ResourceNotFoundException("Trademark not found with id: " + trademarkId));
        return trademarkMapper.trademarkToTrademarkResponseDTO(trademark);
    }

    @Override
    public List<TrademarkResponseDTO> getAllTrademarks() {
        List<Trademark> trademarks = trademarkRepository.findAll();
        List<TrademarkResponseDTO> trademarkResponseDTOS = new ArrayList<>();
        for (Trademark trademark : trademarks) {
            trademarkResponseDTOS.add(trademarkMapper.trademarkToTrademarkResponseDTO(trademark));
        }
        return trademarkResponseDTOS;
    }

    @Override
    public TrademarkResponseDTO updateTrademark(TrademarkRequestDTO trademarkRequestDTO) {
        Trademark trademark = trademarkRepository.findById(trademarkRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Trademark not found with id: " + trademarkRequestDTO.getId()));
        trademarkMapper.updateTrademarkFromTrademarkRequestDTO(trademarkRequestDTO, trademark);
        return trademarkMapper.trademarkToTrademarkResponseDTO(trademarkRepository.save(trademark));
    }

    @Override
    public void deleteTrademark(int trademarkId) {
        Trademark trademark = trademarkRepository.findById(trademarkId)
                .orElseThrow(() -> new ResourceNotFoundException("Trademark not found with id: " + trademarkId));
        trademarkRepository.delete(trademark);
    }
}
