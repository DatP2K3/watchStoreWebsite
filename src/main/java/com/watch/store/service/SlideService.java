package com.watch.store.service;

import com.watch.store.dto.request.SlideRequestDTO;
import com.watch.store.model.Slide;

import java.util.List;

public interface SlideService {
    List<Slide> getAllSlides();

    Slide getSlide(int slideId);

    Slide createSlide(SlideRequestDTO slideRequestDTO);

    Slide updateSlide(SlideRequestDTO slideRequestDTO);

    void deleteSlide(int slideId);
}
