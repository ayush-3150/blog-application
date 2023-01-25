package com.blog.blogapplication;

import org.hibernate.validator.constraints.br.CPF.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.blogapplication.models.Role;
import com.blog.blogapplication.repository.RoleRepository;
import java.util.*;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("encoder" + passwordEncoder.encode("Ag123"));

		try {
			Role roleUser = new Role();
			roleUser.setId(101);
			roleUser.setName("ROLE_USER");

			Role roleAdmin = new Role();
			roleAdmin.setId(100);
			roleAdmin.setName("ROLE_ADMIN");

			ArrayList<Role> roles = new ArrayList<>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			this.roleRepository.saveAll(roles);
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("Could not add Default roles ");
		}
	}

}
