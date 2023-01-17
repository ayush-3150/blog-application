package com.blog.blogapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.models.Category;
import com.blog.blogapplication.models.Post;
import com.blog.blogapplication.models.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findByUser(User user);

    public List<Post> findByCategory(Category category);
}
