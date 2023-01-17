package com.blog.blogapplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.models.Category;
import com.blog.blogapplication.payloads.CategoryDto;
import com.blog.blogapplication.repository.CategoryRepository;
import com.blog.blogapplication.services.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category createdCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(createdCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryName(categoryDto.getCategoryName());

        Category categoryUpdated = this.categoryRepository.save(category);
        return this.modelMapper.map(categoryUpdated, CategoryDto.class);
    }

    @Override
    public void deleteCategory(int id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto getCategory(int id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoris = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categoris.stream()
                .map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

}
