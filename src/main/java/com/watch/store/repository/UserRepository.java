package com.watch.store.repository;

import com.watch.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String username);
}
