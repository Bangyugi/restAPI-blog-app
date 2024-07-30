package com.bangvan.apiblogapp.config;

import com.bangvan.apiblogapp.security.JWTAuthenticationEntryPoint;
import com.bangvan.apiblogapp.security.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        configure ->
                                configure
                                        .requestMatchers("/role/**").hasRole("ADMIN")
                                        .requestMatchers("/auth/**").permitAll()
                                        .requestMatchers(HttpMethod.GET,"/comment/**").permitAll()
                                        .requestMatchers(HttpMethod.GET,"/post/**").permitAll()
                                        .requestMatchers(HttpMethod.GET,"/category/**").permitAll()
                                        .requestMatchers(HttpMethod.GET,"/user/{id}").permitAll()
                                        .requestMatchers("/comment/**").authenticated()
                                        .requestMatchers("/user/**").authenticated()
                                        .requestMatchers("/category/**").authenticated()
                                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception->exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
