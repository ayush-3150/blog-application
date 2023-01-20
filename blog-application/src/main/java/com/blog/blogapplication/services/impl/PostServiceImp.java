package com.blog.blogapplication.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.models.Category;
import com.blog.blogapplication.models.Post;
import com.blog.blogapplication.models.User;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;
import com.blog.blogapplication.repository.CategoryRepository;
import com.blog.blogapplication.repository.PostRepository;
import com.blog.blogapplication.repository.UserRepository;
import com.blog.blogapplication.services.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
                post.setImageUrl(postDto.getImageUrl());

                Post updatedPost = this.postRepository.save(post);
                return this.modelMapper.map(updatedPost, PostDto.class);
        }

        @Override
        public PostDto getPost(int postId) {
                Post post = this.postRepository.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
                return this.modelMapper.map(post, PostDto.class);
        }

        @Override
        public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

                Sort s = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).ascending().descending();

                PageRequest p = PageRequest.of(pageNo, pageSize, s);
                Page<Post> pagePost = this.postRepository.findAll(p);
                List<PostDto> allPosts = pagePost.getContent().stream()
                                .map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

                PostResponse postResponse = new PostResponse();
                postResponse.setContent(allPosts);
                postResponse.setPageNo(pagePost.getNumber());
                postResponse.setTotalElements(pagePost.getNumberOfElements());
                postResponse.setPageSize(pagePost.getSize());
                postResponse.setTotalPages(pagePost.getTotalPages());
                postResponse.setLastPage(pagePost.isLast());

                return postResponse;
        }

        @Override
        public void deletePost(int postId) {
                this.postRepository.deleteById(postId);
        }

        @Override
        public List<PostDto> getPostByCategory(int categoryId) {
                Category category = this.categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
                List<PostDto> postsByCategory = this.postRepository.findByCategory(category).stream()
                                .map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
                return postsByCategory;
        }

        @Override
        public List<PostDto> getPostByUser(int userId) {
                User user = this.userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
                List<PostDto> postsByCategory = this.postRepository.findByUser(user).stream()
                                .map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
                return postsByCategory;
        }

        @Override
        public List<PostDto> searchPosts(String keyword) {

                List<PostDto> posts = this.postRepository.findByTitleContainingIgnoreCase(keyword).stream()
                                .map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
                return posts;
        }

}
