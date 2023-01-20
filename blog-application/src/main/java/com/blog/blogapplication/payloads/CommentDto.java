package com.blog.blogapplication.payloads;

import java.util.List;

import com.blog.blogapplication.models.Post;
import com.blog.blogapplication.models.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private int commentId;

    private String content;

    // private UserDto user;

    // private PostDto post;
}
