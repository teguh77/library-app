package io.tamknown.libapp;

import io.tamknown.libapp.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class LibAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibAppApplication.class, args);
	}

}
