package com.istad.springsecuritybasic.repository;

import com.istad.springsecuritybasic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(String name);
}
