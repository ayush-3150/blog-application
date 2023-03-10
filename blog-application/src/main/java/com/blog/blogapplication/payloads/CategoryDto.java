package com.blog.blogapplication.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int categoryId;
    @NotBlank(message = "Category naame should not be blank")
    @Size(max = 50, message = "Category's name length should not be greater than 50")
    private String categoryName;
    @NotBlank
    @Size(max = 100, message = "Category's description should not be greater than 100")
    private String categoryDescription;
}
