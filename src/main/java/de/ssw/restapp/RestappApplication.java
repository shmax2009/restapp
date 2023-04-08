package de.ssw.restapp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Slf4j
public class RestappApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestappApplication.class, args);
    }
    @Data
    @Configuration
    class AdminUser {
        @Value("${application.security.admin.username}")
        private String username;
        @Value("${application.security.admin.password}")
        private String password;
    }
    @Bean
    CommandLineRunner init(AdminUser admin) {
        return args -> {
            log.info("Admin user: " + admin);
        };
    }
}

