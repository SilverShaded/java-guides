package com.springinaction.tacocloud.Security;

import com.springinaction.tacocloud.Data.UserRepository;
import com.springinaction.tacocloud.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeRequests()
                .mvcMatchers("/design", "/orders").access("hasRole('USER')")
                .anyRequest().access("permitAll()")

            .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/design")

            .and()
                .logout()
                    .logoutSuccessUrl("/")

            .and()
                .build();
    }


}
