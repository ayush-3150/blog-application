package com.blog.blogapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.CommentDto;
import com.blog.blogapplication.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("users/{userId}/posts/{postId}/comment")
    
    public CommentDto createComment(@RequestBody CommentDto commentDto, @PathVariable int userId,
            @PathVariable int postId) {

        CommentDto createdComment = this.commentService.createComment(commentDto, postId, userId);
        

        return createdComment;
    }

    @DeleteMapping("users/{userId}/posts/{postId}/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId, @PathVariable int userId,
            @PathVariable int postId) {
        this.commentService.deleteComment(commentId, postId, userId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Comment Deleted succesfully!");
        apiResponse.setSuccess(true);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
