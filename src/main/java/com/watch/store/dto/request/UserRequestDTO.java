package com.watch.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {
    int id;
    @NotBlank(message = "Email cannot be blank")
    String email;
    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&-+=()!?\"]).{8,128}$", message = "Password must be at least 8 characters long and contain at least one letter and one number one special character and one uppercase letter")
    String password;
    @NotBlank(message = "Avatar cannot be blank")
    @URL(message = "Avatar must be a valid URL")
    String avatar;
    @NotNull(message = "Role cannot be null")
    int roleID;
}
