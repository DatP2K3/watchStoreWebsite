package com.watch.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequestDTO {
    int id;
    @NotBlank(message = "Role name cannot be blank")
    @Pattern(regexp = "^ROLE_.*", message = "Role name must start with ROLE_")
    String name;
}
