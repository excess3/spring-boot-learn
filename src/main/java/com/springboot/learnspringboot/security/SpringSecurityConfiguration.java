package com.springboot.learnspringboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {

        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated() // say that we want to authenticate any request
        );

        http.httpBasic(Customizer.withDefaults()); //now set how we want to authenticate

        http.csrf().disable();// disable post and put csrf token validation

        return http.build();
    }
}
