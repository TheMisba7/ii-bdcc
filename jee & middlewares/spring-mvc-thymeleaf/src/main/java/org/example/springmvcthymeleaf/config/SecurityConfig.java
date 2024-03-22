package org.example.springmvcthymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private static final String [] ALLOWED_PATTERNS = {"/login", "/register"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        https.csrf(AbstractHttpConfigurer::disable);
        https.formLogin(customizer ->
                customizer.loginPage("/login")
                        .defaultSuccessUrl("/patients", true)
        ).authorizeHttpRequests(cu ->
                cu.requestMatchers(ALLOWED_PATTERNS)
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/patients").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .anyRequest().authenticated()
        );

        return https.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
