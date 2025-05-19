package com.shopverse.shopverse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/users/create").permitAll()
                .requestMatchers("/api/users/login").permitAll()
                .requestMatchers("/api/users/details/**").hasAnyRole("CUSTOMER","ADMIN")
                .requestMatchers("/api/users/update/**").hasAnyRole("CUSTOMER","ADMIN")
                .requestMatchers("/api/users/delete/**").hasRole("ADMIN")
                .requestMatchers("/api/products/all").permitAll()
                .requestMatchers("/api/products/add").hasRole("ADMIN")
                .requestMatchers("/api/products/getproduct/**").permitAll()
                .requestMatchers("/api/products/update/**").hasRole("ADMIN")
                .requestMatchers("/api/products/delete/**").hasRole("ADMIN")
                .requestMatchers("/api/products/search").permitAll()
                .requestMatchers("/api/orders/**").permitAll()
                .requestMatchers("/api/cart-items/**").permitAll()
                .requestMatchers("/api/order-items/**").permitAll()
                .requestMatchers("/api/order-items/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}