package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.application.repository.IUserRepository;
import com.bitspondon.quiz.application.usecase.IAuthenticationUseCase;
import com.bitspondon.quiz.domain.AllEnums;
import com.bitspondon.quiz.domain.dto.auth.AuthenticationRequest;
import com.bitspondon.quiz.domain.dto.auth.AuthenticationResponse;
import com.bitspondon.quiz.domain.dto.auth.RegisterRequest;
import com.bitspondon.quiz.domain.entities.User;
import com.bitspondon.quiz.presentation.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase implements IAuthenticationUseCase {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public void createUser(RegisterRequest request) {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(String.valueOf(AllEnums.Role.USER))
                .build();
        repository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
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

    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletRequest http) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = (User) repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtUtils.generateToken(user);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(http)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
//        }
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
