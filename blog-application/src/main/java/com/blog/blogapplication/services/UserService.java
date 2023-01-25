package com.blog.blogapplication.services;

import java.util.List;

import com.blog.blogapplication.payloads.UserDto;

public interface UserService {
    UserDto registerUser(UserDto userDto);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    List<UserDto> getAllUsers();

    UserDto getUser(Integer userId);

    void deleteUser(Integer userId);

}
