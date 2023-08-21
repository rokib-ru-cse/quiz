package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.dto.auth.AuthenticationRequest;
import com.bitspondon.quiz.domain.dto.auth.AuthenticationResponse;
import com.bitspondon.quiz.domain.dto.auth.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface IAuthenticationUseCase {
    void createAdmin(RegisterRequest request);
    AuthenticationResponse createUser(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletRequest http);
}
