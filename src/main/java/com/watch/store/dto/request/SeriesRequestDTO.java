package com.watch.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeriesRequestDTO {
    int id;
    @NotBlank(message = "Series name cannot be blank")
    String name;
    @Positive(message = "Trademark ID must be positive")
    int trademarkID;
}
