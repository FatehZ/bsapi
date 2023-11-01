package com.ktxdevelopment.authservice.security.config;


import com.ktxdevelopment.authservice.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.ktxdevelopment.authservice.model.Role.*;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((requests) -> requests

                        .requestMatchers("/api/v1/book/**").permitAll() //todo

                        .requestMatchers("/api/v1/student/**").hasRole(STUDENT.name())

                        .requestMatchers("/api/v1/author/**").hasRole(AUTHOR.name())

                        .anyRequest().authenticated()
                ).sessionManagement((sec) -> sec.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .logout((form) ->
                        form.logoutUrl("/api/v1/auth/logout").addLogoutHandler(logoutHandler).logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                        .permitAll()
                );

        return http.build();
    }
}
