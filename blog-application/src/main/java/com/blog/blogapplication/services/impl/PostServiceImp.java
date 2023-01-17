package com.blog.blogapplication.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.models.Category;
import com.blog.blogapplication.models.Post;
import com.blog.blogapplication.models.User;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.repository.CategoryRepository;
import com.blog.blogapplication.repository.PostRepository;
import com.blog.blogapplication.repository.UserRepository;
import com.blog.blogapplication.services.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    public PostRepository postRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryid) {
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageUrl("default.png");
        post.setAddedDate(new Date());

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        ;
        Category category = this.categoryRepository.findById(categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryid));
        ;
        post.setCategory(category);
        post.setUser(user);
        Post createdPost = this.postRepository.save(post);
        return this.modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatedPost = this.postRepository.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostDto getPost(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostDto> getAllPost() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletePost(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostDto> getPostByUser(int user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }

}
