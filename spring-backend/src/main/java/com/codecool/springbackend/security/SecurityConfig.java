package com.codecool.springbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests((auth) -> {
                    auth.anyRequest().permitAll();
                    /*auth.requestMatchers("/").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/").hasAnyAuthority(Role.USER.toString(), Role.ADMIN.toString());
                    auth.anyRequest().authenticated();*/
                }).sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );


        return http.build();
    }
}
