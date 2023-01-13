package com.blog.blogapplication.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.payloads.UserDto;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    List<UserDto> getAllUsers();

    UserDto getUser(Integer userId);

    void deleteUser(UserDto userDto, Integer userId);

}
