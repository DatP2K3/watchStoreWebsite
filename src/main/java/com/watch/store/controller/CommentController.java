package com.watch.store.controller;

import com.watch.store.dto.request.CommentRequestDTO;
import com.watch.store.dto.response.CommentResponseDTO;
import com.watch.store.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentResponseDTO = commentService.createComment(commentRequestDTO);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @PutMapping("/comments")
    ResponseEntity<CommentResponseDTO> updateComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentResponseDTO = commentService.updateComment(commentRequestDTO);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/comments/{commentID}")
    ResponseEntity<Void> deleteComment(@PathVariable int commentID) {
        commentService.deleteComment(commentID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comments/product/{productID}")
    ResponseEntity<List<CommentResponseDTO>> getCommentByProductId(@PathVariable int productID) {
        List<CommentResponseDTO> commentResponseDTOS = commentService.getCommentByProductId(productID);
        return ResponseEntity.ok(commentResponseDTOS);
    }

    @GetMapping("/comments/user/{userId}")
    ResponseEntity<List<CommentResponseDTO>> getCommentByUserId(@PathVariable int userId) {
        List<CommentResponseDTO> commentResponseDTOS = commentService.getCommentByUserId(userId);
        return ResponseEntity.ok(commentResponseDTOS);
    }

    @GetMapping("/comments/{commentID}")
    ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable int commentID) {
        CommentResponseDTO commentResponseDTO = commentService.getCommentById(commentID);
        return ResponseEntity.ok(commentResponseDTO);
    }
}
