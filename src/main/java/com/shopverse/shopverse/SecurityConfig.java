package com.shopverse.shopverse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/users/create").permitAll()
                .requestMatchers("/api/users/login/**").permitAll()
                .requestMatchers("/api/users/details/**").hasAnyRole("CUSTOMER","ADMIN")
                .requestMatchers("/api/users/update/**").hasAnyRole("CUSTOMER","ADMIN")
                .requestMatchers("/api/users/delete/**").hasRole("ADMIN")
                .requestMatchers("/api/products/add").hasRole("ADMIN")
                .requestMatchers("/api/products/getproduct/**","/api/products/all").permitAll()
                .requestMatchers("/api/products/update/*").hasRole("ADMIN")
                .requestMatchers("/api/products/delete/**").hasRole("ADMIN")
                .requestMatchers("/api/products/search").permitAll()
               .requestMatchers("/api/orders/**").hasAnyRole("CUSTOMER", "DELIVERY", "ADMIN")
                .requestMatchers("/api/cart-items/**").permitAll()
                .requestMatchers("/api/order-items/**").permitAll()
                .requestMatchers("/api/order-items/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
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