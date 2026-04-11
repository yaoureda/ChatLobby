package com.personal.chatlobby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/register",
                                "/pages/login.html",
                                "/pages/register.html",
                                "/js/**",
                                "/css/**",
                                "/error"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/pages/login.html")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/pages/chat.html", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/pages/login.html")
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
