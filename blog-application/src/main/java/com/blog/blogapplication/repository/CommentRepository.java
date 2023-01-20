package com.blog.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.models.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer>{
    
}
