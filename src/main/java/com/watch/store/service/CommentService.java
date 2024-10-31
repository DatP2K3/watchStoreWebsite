package com.watch.store.service;

import com.watch.store.dto.request.CommentRequestDTO;
import com.watch.store.dto.response.CommentResponseDTO;

import java.util.List;

public interface CommentService {
    List<CommentResponseDTO> getCommentByProductId(int productId);
    List<CommentResponseDTO> getCommentByUserId(int userId);
    CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO);
    CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO);
    void deleteComment(int commentId);
    CommentResponseDTO getCommentById(int commentId);
}
