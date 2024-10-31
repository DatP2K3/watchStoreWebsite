package com.watch.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequestDTO {
    int id;
    @Positive(message = "Rate must be positive")
    int rate;
    @NotBlank(message = "Comment cannot be blank")
    String comment;
    @Positive(message = "User ID must be positive")
    int userID;
    @Positive(message = "Product ID must be positive")
    int ProductID;
}
