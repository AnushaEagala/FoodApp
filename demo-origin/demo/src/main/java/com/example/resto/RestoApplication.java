package com.example.resto;

import com.example.resto.role.Role;
import com.example.resto.role.RoleRepository;
import com.example.resto.user.User;
import com.example.resto.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Collections;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class RestoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestoApplication.class, args);
	}


	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository, UserRepository userRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(Role.builder().name("USER").build());
			}
			if (roleRepository.findByName("ADMIN").isEmpty()) {
				roleRepository.save(Role.builder().name("ADMIN").build());
			}
		/*	User admin = User.builder()
					.firstname("Admin")
					.lastname("User")
					.email("admin@admin.com")
					.password("password11")
					.accountlocked(false)
					.enabled(true)
					.roles(Collections.singletonList(roleRepository.findByName("ADMIN").orElseThrow()))
					.build();
			userRepository.save(admin);
		}; */


		};
	}
}

