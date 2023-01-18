package com.blog.blogapplication.payloads;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    @NotBlank(message = "Content should not be blank")
    @Size(max = 10000, message = "Content size should not be greater than 10000 character")
    private String content;
    @NotBlank(message = "Title should not be blank")
    private String title;
    private String imageUrl;
    private Date addedDate;

    private CategoryDto category;

    private UserDto user;
}
