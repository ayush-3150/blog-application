package com.blog.blogapplication.services;

import com.blog.blogapplication.payloads.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto, int postId, int userId);

    public void deleteComment(int commentId,int postId, int userId);

}
