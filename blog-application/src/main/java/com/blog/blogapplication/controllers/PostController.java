package com.blog.blogapplication.controllers;

import javax.validation.Valid;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapplication.models.Post;
import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;
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

    @PutMapping("posts/{postId}")
    public PostDto updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("postId") int postId) {

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

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {
        this.postService.deletePost(postId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Post Deleted succesfully!");
        apiResponse.setSuccess(true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public PostResponse getAllPosts(@RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy, 
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir)  {
        PostResponse posts = this.postService.getAllPost(pageNum, pageSize, sortBy,sortDir);
        return posts;
    }

    @GetMapping("/posts/{postId}")
    public PostDto getPost(@PathVariable int postId) {
        PostDto post = this.postService.getPost(postId);
        return post;
    }
}
