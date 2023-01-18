package com.blog.blogapplication.payloads;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDto> content;
    private int pageNo;
    private int totalElements;
    private int pageSize;
    private int totalPages;
    private boolean lastPage;
}
