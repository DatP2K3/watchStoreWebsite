package com.watch.store.service;

import com.watch.store.dto.request.TrademarkRequestDTO;
import com.watch.store.dto.response.TrademarkResponseDTO;
import com.watch.store.model.Trademark;

import java.util.List;

public interface TrademarkService {
    TrademarkResponseDTO createTrademark(TrademarkRequestDTO trademarkRequestDTO);
    TrademarkResponseDTO getTrademark(int trademarkId);
    List<TrademarkResponseDTO> getAllTrademarks();
    TrademarkResponseDTO updateTrademark(TrademarkRequestDTO trademarkRequestDTO);
    void deleteTrademark(int trademarkId);
}
