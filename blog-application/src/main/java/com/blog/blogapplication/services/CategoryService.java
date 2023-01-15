package com.blog.blogapplication.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.blogapplication.payloads.CategoryDto;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(CategoryDto categoryDto, int id);

    public void deleteCategory(int id);

    public CategoryDto getCategory(int id);

    public List<CategoryDto> getAllCategories();

}
