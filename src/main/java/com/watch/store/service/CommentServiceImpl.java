package com.watch.store.service;

import com.watch.store.dto.request.CommentRequestDTO;
import com.watch.store.dto.response.CommentResponseDTO;
import com.watch.store.exception.ResourceNotFoundException;
import com.watch.store.mapper.CommentMapper;
import com.watch.store.model.Comment;
import com.watch.store.model.Product;
import com.watch.store.model.User;
import com.watch.store.repository.CommentRepository;
import com.watch.store.repository.ProductRepository;
import com.watch.store.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentResponseDTO> getCommentByProductId(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        List<Comment> comments = commentRepository.getCommentsByProduct(product);
        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponseDTOS.add(commentMapper.commentToCommentResponseDTO(comment));
        }
        return commentResponseDTOS;
    }

    @Override
    public List<CommentResponseDTO> getCommentByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        List<Comment> comments = commentRepository.getCommentsByUser(user);
        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponseDTOS.add(commentMapper.commentToCommentResponseDTO(comment));
        }
        return commentResponseDTOS;
    }

    @Transactional
    @Override
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        Comment comment = commentMapper.commentRequestDTOToComment(commentRequestDTO);
        User user = userRepository.findById(commentRequestDTO.getUserID())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + commentRequestDTO.getUserID()));
        Product product = productRepository.findById(commentRequestDTO.getProductID())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + commentRequestDTO.getProductID()));
        user.addComment(comment);
        product.addComment(comment);
        return commentMapper.commentToCommentResponseDTO(commentRepository.save(comment));
    }

    @Override
    public CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO) {
        Comment comment = commentRepository.findById(commentRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentRequestDTO.getId()));
        commentMapper.updateCommentFromCommentRequestDTO(commentRequestDTO, comment);
        return commentMapper.commentToCommentResponseDTO(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
        User user = comment.getUser();
        user.getComments().remove(comment);
        Product product = comment.getProduct();
        product.getComments().remove(comment);
        commentRepository.delete(comment);
    }

    @Override
    public CommentResponseDTO getCommentById(int commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
        return commentMapper.commentToCommentResponseDTO(comment);
    }
}
