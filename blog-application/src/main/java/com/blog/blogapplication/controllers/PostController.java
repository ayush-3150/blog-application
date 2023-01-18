package com.blog.blogapplication.controllers;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapplication.models.Post;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public PostDto createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") int userId,
            @PathVariable("categoryId") int categoryId) {
        return this.postService.createPost(postDto, userId, categoryId);
    }

    @PutMapping("/user/{userId}/category/{categoryId}/posts/{postId}")
    public PostDto updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") int userId,
            @PathVariable("categoryId") int categoryId, @PathVariable("postId") int postId) {

        return this.postService.updatePost(postDto, postId);
    }

    @GetMapping("/user/{userId}/posts")
    public List<PostDto> getPostsByUser(@PathVariable("userId") int userId) {

        List<PostDto> postsByUser = this.postService.getPostByUser(userId);
        return postsByUser;
    }

    @GetMapping("/category/{categoryId}/posts")
    public List<PostDto> getPostsByCategory(@PathVariable("categoryId") int categoryId) {

        List<PostDto> postsByCategory = this.postService.getPostByCategory(categoryId);
        return postsByCategory;
    }

    @GetMapping("/posts/search/{keyword}")
    public List<PostDto> searchPost(@PathVariable String keyword) {
        List<PostDto> posts = this.postService.searchPosts(keyword);
    
        return posts;
    }
}
