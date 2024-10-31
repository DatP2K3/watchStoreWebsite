package com.watch.store.repository;

import com.watch.store.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Boolean existsByName(String name);
}
