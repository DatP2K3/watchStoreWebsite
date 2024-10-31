package com.watch.store.mapper;

import com.watch.store.dto.request.TrademarkRequestDTO;
import com.watch.store.dto.response.TrademarkResponseDTO;
import com.watch.store.model.Trademark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {SeriesMapper.class})
public interface TrademarkMapper {
    Trademark trademarkRequestDTOToTrademark(TrademarkRequestDTO trademarkRequestDTO);

    TrademarkResponseDTO trademarkToTrademarkResponseDTO(Trademark trademark);

    @Mapping(target = "id", ignore = true)
    void updateTrademarkFromTrademarkRequestDTO(TrademarkRequestDTO trademarkRequestDTO, @MappingTarget Trademark trademark);
}
