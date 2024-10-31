package com.watch.store.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailRequestDTO {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    String to;
    @NotBlank(message = "Subject cannot be blank")
    String subject;
    @NotBlank(message = "Email body cannot be blank")
    String body;
}
