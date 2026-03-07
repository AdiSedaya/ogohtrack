package com.ogoh.ogohtrack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ogoh.ogohtrack.repository.UserRepository;

/* 
   Kelas utama untuk menjalankan OgohTrack ini .
*/
@SpringBootApplication
public class OgohtrackApplication {

	/* Method utama  Spring Boot */
	public static void main(String[] args) {
		SpringApplication.run(OgohtrackApplication.class, args);
	}

	@Bean
CommandLineRunner init(UserRepository userRepository){
    return args -> {

        if(userRepository.findByUsername("admin")==null){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRole("admin");
            admin.setNama("Administrator");
            admin.setHp("000");
            userRepository.save(admin);
        }

        if(userRepository.findByUsername("user")==null){
            User user = new User();
            user.setUsername("user");
            user.setPassword("user123");
            user.setRole("user");
            user.setNama("User");
            user.setHp("000");
            userRepository.save(user);
        }

    };
}

}