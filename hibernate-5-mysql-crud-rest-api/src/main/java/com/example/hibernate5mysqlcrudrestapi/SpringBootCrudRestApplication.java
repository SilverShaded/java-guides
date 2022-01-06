package com.example.hibernate5mysqlcrudrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootCrudRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudRestApplication.class, args);
    }

}
