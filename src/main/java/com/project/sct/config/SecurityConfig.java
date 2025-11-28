package com.project.sct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Aama Badhi API Call Thai jase km kia Login Requird narthi aetle
 * aa delete kari de file config vali
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth

                        // Public API/auth pages
                        .requestMatchers("/api/auth/**", "/css/**", "/js/**", "/images/**", "/")
                        .permitAll()
                        // All other URLs require login
                        .anyRequest().authenticated()
                )
                // disable basic auth
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
