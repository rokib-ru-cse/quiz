package com.sigma.quiz.application.usecase.impl;

import com.sigma.quiz.application.repository.IUserRepository;
import com.sigma.quiz.application.usecase.IAuthenticationUseCase;
import com.sigma.quiz.domain.AllEnums;
import com.sigma.quiz.domain.dto.auth.AuthenticationRequest;
import com.sigma.quiz.domain.dto.auth.AuthenticationResponse;
import com.sigma.quiz.domain.dto.auth.RegisterRequest;
import com.sigma.quiz.domain.entities.User;
import com.sigma.quiz.presentation.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase implements IAuthenticationUseCase {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        com.sigma.quiz.domain.entities.User user = com.sigma.quiz.domain.entities.User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(String.valueOf(AllEnums.Role.USER))
                .build();
        repository.save(user);
        var jwtToken = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        com.sigma.quiz.domain.entities.User user = (User) repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
