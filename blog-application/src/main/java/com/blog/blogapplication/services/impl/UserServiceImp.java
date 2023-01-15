package com.blog.blogapplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.models.User;
import com.blog.blogapplication.payloads.UserDto;
import com.blog.blogapplication.repository.UserRepository;
import com.blog.blogapplication.services.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepository.save(user);

        // User updatedUser = this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return userToDto(user);
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDto;
    }

    public User dtoToUser(UserDto dto) {
        // Using model mapper class to conver UserDto obj to User obj
        User user = this.modelMapper.map(dto, User.class);

        // user.setId(dto.getId());
        // user.setName(dto.getName());
        // user.setEmail(dto.getEmail());
        // user.setPassword(dto.getPassword());
        // user.setAbout(dto.getAbout());
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto dto = this.modelMapper.map(user, UserDto.class);
        // dto.setId(user.getId());
        // dto.setName(user.getName());
        // dto.setEmail(user.getEmail());
        // dto.setPassword(user.getPassword());
        // dto.setAbout(user.getAbout());
        return dto;
    }

}
