package com.bitspondon.quiz.presentation.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        String[] publicUrls = new String[]{"/register", "/login", "/api/v1/auth/signup", "/api/v1/auth/login", "swagger-ui/**", "/swagger-ui.html", "/swagger-ui*/*swagger-initializer.js", "/swagger-ui*/**", "/webjars/**", "/register", "/css/**", "/js/**", "/images/**",};
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(publicUrls).permitAll().anyRequest().authenticated()).csrf(AbstractHttpConfigurer::disable).formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/").loginProcessingUrl("/login").failureUrl("/login?error=true").permitAll()).logout(out -> out.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}



