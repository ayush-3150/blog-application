package com.blog.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapplication.models.Role;

public interface RoleRepository  extends JpaRepository<Role,Integer>{
    
}
