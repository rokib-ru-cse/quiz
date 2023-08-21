package com.bitspondon.quiz.presentation.security;

import com.bitspondon.quiz.application.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final IUserRepository repository;


    @Bean
    public UserDetailsService loadUserByUsername() {
        return username -> (UserDetails) repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
//
//

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        List<AuthenticationProvider> authenticationProviders = Arrays.asList(authenticationProvider());
//        return new ProviderManager(authenticationProviders);
//    }

    //    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
