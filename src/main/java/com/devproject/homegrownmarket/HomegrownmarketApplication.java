package com.devproject.homegrownmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.devproject.homegrownmarket")
public class HomegrownmarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomegrownmarketApplication.class, args);
    }

}
