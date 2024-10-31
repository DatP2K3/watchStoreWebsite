package com.watch.store.service;

import com.watch.store.dto.request.EmailRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    public void sendSimpleEmail(EmailRequestDTO emailRequestDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(emailRequestDTO.getTo());
        message.setSubject(emailRequestDTO.getSubject());
        message.setText(emailRequestDTO.getBody());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.err.println("Error sending email: " + e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
