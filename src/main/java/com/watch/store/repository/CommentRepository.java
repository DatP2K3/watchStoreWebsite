package com.watch.store.repository;

import com.watch.store.model.Comment;
import com.watch.store.model.Product;
import com.watch.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getCommentsByUser(User user);
    List<Comment> getCommentsByProduct(Product product);
}
