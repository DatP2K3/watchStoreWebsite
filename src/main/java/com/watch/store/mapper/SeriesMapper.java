package com.watch.store.mapper;

import com.watch.store.dto.request.SeriesRequestDTO;
import com.watch.store.dto.response.SeriesResponseDTO;
import com.watch.store.model.Series;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface SeriesMapper {
    Series seriesRequestDTOToSeries(SeriesRequestDTO seriesRequestDTO);

    SeriesResponseDTO seriesToSeriesResponseDTO(Series series);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trademark", ignore = true)
    void updateSeriesFromSeriesRequestDTO(SeriesRequestDTO seriesRequestDTO, @MappingTarget Series series);
}
