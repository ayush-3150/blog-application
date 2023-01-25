package com.blog.blogapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapplication.Security.JWTTokenHelper;
import com.blog.blogapplication.payloads.JwtAuthRequest;
import com.blog.blogapplication.payloads.JwtAuthResponse;
import com.blog.blogapplication.payloads.UserDto;
import com.blog.blogapplication.services.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public UserDto registerUser(@RequestBody UserDto userDto) {
        UserDto registerdUser = this.userService.registerUser(userDto);
        return registerdUser;
    }

    @PostMapping("login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                username, password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        } catch (DisabledException e) {
            System.out.println("User is disables");
            throw new Exception("Invalid creds");
        }
    }
}
