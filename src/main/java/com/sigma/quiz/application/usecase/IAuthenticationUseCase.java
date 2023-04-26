package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.dto.auth.AuthenticationRequest;
import com.sigma.quiz.domain.dto.auth.AuthenticationResponse;
import com.sigma.quiz.domain.dto.auth.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface IAuthenticationUseCase {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
