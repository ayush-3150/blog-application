package com.blog.blogapplication.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, message = "Name's length should be 5 or more than 5 characters")
    private String name;
    @Email(message = "Email address should be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "Password should not be blank")
    @Size(min = 5, max = 10, message = "Password's length should be 5 or more than 5 characters")
    private String password;

    @NotEmpty
    @Size(max = 50, message = "Bio should not be more than 50 characters")
    // @Pattern(regexp
    // ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$")
    private String about;
}
