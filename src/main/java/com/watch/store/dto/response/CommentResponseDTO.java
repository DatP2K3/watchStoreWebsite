package com.watch.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponseDTO {
    int id;
    int rate;
    String comment;
    UserResponseDTO userResponseDTO;
    ProductResponseDTO productResponseDTO;
}
