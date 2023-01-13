package com.blog.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.models.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    
}
