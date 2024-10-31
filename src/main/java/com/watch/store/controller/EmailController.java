package com.watch.store.controller;

import com.watch.store.dto.request.EmailRequestDTO;
import com.watch.store.service.EmailService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send-email")
    ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailRequestDTO emailRequestDTO) {
        emailService.sendSimpleEmail(emailRequestDTO);
        return ResponseEntity.ok().build();
    }
}
