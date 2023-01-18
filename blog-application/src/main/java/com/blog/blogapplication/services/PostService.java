package com.blog.blogapplication.services;

import java.util.List;

import com.blog.blogapplication.models.Category;
import com.blog.blogapplication.models.Post;
import com.blog.blogapplication.models.User;
import com.blog.blogapplication.payloads.PostDto;
import com.blog.blogapplication.payloads.PostResponse;

public interface PostService {
    public PostDto createPost(PostDto postDto, int userId, int categoryId);

    public PostDto updatePost(PostDto postDto, int id);

    public PostDto getPost(int id);

    public PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);

    public void deletePost(int id);

    public List<PostDto> getPostByCategory(int categoryId);

    public List<PostDto> getPostByUser(int user);

    // Search post by entering keyword
    public List<PostDto> searchPosts(String keyword);
}
