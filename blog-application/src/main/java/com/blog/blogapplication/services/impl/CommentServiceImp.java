package com.blog.blogapplication.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.models.Comment;
import com.blog.blogapplication.models.Post;
import com.blog.blogapplication.models.User;
import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.CommentDto;
import com.blog.blogapplication.repository.CommentRepository;
import com.blog.blogapplication.repository.PostRepository;
import com.blog.blogapplication.repository.UserRepository;
import com.blog.blogapplication.services.CommentService;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, int postId, int userId) {
        // TODO Auto-generated method stub
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);

        Comment createdComment = this.commentRepository.save(comment);

        CommentDto createdComentDto = this.modelMapper.map(createdComment, CommentDto.class);

        return createdComentDto;

    }

    @Override
    public void deleteComment(int commentId, int postId, int userId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        this.commentRepository.delete(comment);
    }

}