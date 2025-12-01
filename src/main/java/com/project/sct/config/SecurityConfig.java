package com.project.sct.config;

import com.project.sct.security.EmailPasswordAuthFilter;
import com.project.sct.security.JwtAuthSuccessHandler;
import com.project.sct.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthSuccessHandler successHandler;
    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthSuccessHandler successHandler, JwtAuthenticationFilter jwtFilter) {
        this.successHandler = successHandler;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        var emailFilter = new EmailPasswordAuthFilter();
        emailFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        emailFilter.setAuthenticationSuccessHandler(successHandler);

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterAt(emailFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter, EmailPasswordAuthFilter.class)
                .formLogin(form -> form.loginPage("/auth/login").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
