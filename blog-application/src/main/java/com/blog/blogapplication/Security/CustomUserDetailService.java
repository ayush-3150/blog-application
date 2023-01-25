package com.blog.blogapplication.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.blog.blogapplication.models.User;
import com.blog.blogapplication.repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email:" + username, 0));
        return user;
    }

}
