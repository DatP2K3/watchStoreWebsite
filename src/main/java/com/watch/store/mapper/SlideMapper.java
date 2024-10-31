package com.watch.store.mapper;

import com.watch.store.dto.request.SlideRequestDTO;
import com.watch.store.model.Slide;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SlideMapper {
    Slide slideRequestDTOToSlide(SlideRequestDTO slideRequestDTO);
    @Mapping(target = "id", ignore = true)
    void updateSlideFromSlideRequestDTO(SlideRequestDTO slideRequestDTO, @MappingTarget Slide slide);
}
