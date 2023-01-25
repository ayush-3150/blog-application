package com.blog.blogapplication.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapplication.payloads.ApiResponse;
import com.blog.blogapplication.payloads.UserDto;
import com.blog.blogapplication.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto createdUserDto = this.userService.createUser(userDto);

        HttpHeaders responseHeaders = new HttpHeaders();
        System.out.println("response header= " + responseHeaders);
        // return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
        return ResponseEntity.ok().headers(responseHeaders).body(createdUserDto);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {
        UserDto updatedUser = this.userService.updateUser(userDto, id);
        return ResponseEntity.ok().body(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        this.userService.deleteUser(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("User Deleted succesfully");
        apiResponse.setSuccess(true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Integer id) {
        UserDto user = this.userService.getUser(id);
        return user;
    }

    @GetMapping("/")
    public List<UserDto> getAllUsers() {
        List<UserDto> users = this.userService.getAllUsers();
        return users;
    }

}
