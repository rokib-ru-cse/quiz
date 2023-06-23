package com.sigma.quiz.application.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface IUserUseCase {
    ResponseEntity<String> signUp();
}
