package com.watch.store.service;

import com.watch.store.dto.request.SlideRequestDTO;
import com.watch.store.exception.ResourceNotFoundException;
import com.watch.store.mapper.SlideMapper;
import com.watch.store.model.Slide;
import com.watch.store.repository.SlideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {
    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;

    @Override
    public List<Slide> getAllSlides() {
        return slideRepository.findAll();
    }

    @Override
    public Slide getSlide(int slideId) {
        return slideRepository.findById(slideId)
                .orElseThrow(() -> new ResourceNotFoundException("Slide not found with id: " + slideId));
    }

    @Override
    public Slide createSlide(SlideRequestDTO slideRequestDTO) {
        return slideRepository.save(slideMapper.slideRequestDTOToSlide(slideRequestDTO));
    }

    @Override
    public Slide updateSlide(SlideRequestDTO slideRequestDTO) {
        Slide slide = slideRepository.findById(slideRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Slide not found with id: " + slideRequestDTO.getId()));
        slideMapper.updateSlideFromSlideRequestDTO(slideRequestDTO, slide);
        return slideRepository.save(slide);
    }

    @Override
    public void deleteSlide(int slideId) {
        slideRepository.findById(slideId)
                .ifPresent(slideRepository::delete);
    }
}
