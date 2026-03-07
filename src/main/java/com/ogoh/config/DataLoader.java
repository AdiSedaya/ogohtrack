package com.ogoh.ogohtrack.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ogoh.ogohtrack.model.User;
import com.ogoh.ogohtrack.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository repo) {
        return args -> {

            if (repo.count() == 0) {

                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin123");

                User user = new User();
                user.setUsername("user");
                user.setPassword("user123");

                repo.save(admin);
                repo.save(user);

            }
        };
    }
}
