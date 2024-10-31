package com.watch.store.mapper;

import com.watch.store.dto.request.CommentRequestDTO;
import com.watch.store.dto.response.CommentResponseDTO;
import com.watch.store.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentRequestDTOToComment(CommentRequestDTO commentRequestDTO);

    @Mapping(source = "user", target = "userResponseDTO")
    @Mapping(source = "product", target = "productResponseDTO")
    CommentResponseDTO commentToCommentResponseDTO(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateCommentFromCommentRequestDTO(CommentRequestDTO commentRequestDTO,@MappingTarget Comment comment);
}
