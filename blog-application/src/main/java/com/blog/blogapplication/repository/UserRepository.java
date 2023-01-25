package com.blog.blogapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
