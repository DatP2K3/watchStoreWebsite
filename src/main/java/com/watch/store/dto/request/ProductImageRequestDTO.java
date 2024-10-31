package com.watch.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductImageRequestDTO {
    int id;
    @NotBlank(message = "Image cannot be blank")
    @URL(message = "Image must be a valid URL")
    String image;
}
