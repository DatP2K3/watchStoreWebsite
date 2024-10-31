package com.watch.store.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InfoUserRequestDTO {
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&-+=()!?\"]).{8,128}$", message = "Password must be at least 8 characters long and contain at least one letter and one number one special character and one uppercase letter")
    String password;
    @Email(message = "Invalid email format")
    String email;
    @URL(message = "Avatar must be a valid URL")
    String avatar;
}
