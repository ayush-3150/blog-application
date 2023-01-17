package com.blog.blogapplication.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
