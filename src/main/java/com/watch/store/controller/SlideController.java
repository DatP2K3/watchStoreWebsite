package com.watch.store.controller;

import com.watch.store.dto.request.SlideRequestDTO;
import com.watch.store.model.Slide;
import com.watch.store.service.SlideService;
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
public class SlideController {
    private final SlideService slideService;

    @PostMapping("/slides")
    ResponseEntity<Slide> createSlide(@Valid @RequestBody SlideRequestDTO slideRequestDTO) {
        Slide slide = slideService.createSlide(slideRequestDTO);
        return ResponseEntity.ok(slide);
    }

    @GetMapping("/slides")
    ResponseEntity<List<Slide>> getAllSlides() {
        List<Slide> slide = slideService.getAllSlides();
        return ResponseEntity.ok(slide);
    }

    @GetMapping("/slides/{slideId}")
    ResponseEntity<Slide> getSlide(@PathVariable int slideId) {
        Slide slide = slideService.getSlide(slideId);
        return ResponseEntity.ok(slide);
    }

    @PutMapping("/slides")
    ResponseEntity<Slide> updateSlide(@Valid @RequestBody SlideRequestDTO slideRequestDTO) {
        Slide slide = slideService.updateSlide(slideRequestDTO);
        return ResponseEntity.ok(slide);
    }

    @DeleteMapping("/slides/{slideId}")
    ResponseEntity<Void> deleteSlide(@PathVariable int slideId) {
        slideService.deleteSlide(slideId);
        return ResponseEntity.noContent().build();
    }
}
