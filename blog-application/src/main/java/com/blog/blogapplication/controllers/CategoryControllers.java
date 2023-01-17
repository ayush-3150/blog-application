package com.blog.blogapplication.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.CategoryDto;
import com.blog.blogapplication.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryControllers {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto categoryDtoCreated = this.categoryService.createCategory(categoryDto);
        return ResponseEntity.ok().body(categoryDtoCreated);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {

        List<CategoryDto> categoryDtos = this.categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int categoryId) {

        CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
        return ResponseEntity.ok().body(categoryDto);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
            @PathVariable int categoryId) {

        CategoryDto categorUpadated = this.categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok().body(categorUpadated);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId) {

        this.categoryService.deleteCategory(categoryId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Category Deleted succesfully");
        apiResponse.setSuccess(true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

}
