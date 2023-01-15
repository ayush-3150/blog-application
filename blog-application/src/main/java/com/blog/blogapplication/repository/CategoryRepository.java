package com.blog.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
