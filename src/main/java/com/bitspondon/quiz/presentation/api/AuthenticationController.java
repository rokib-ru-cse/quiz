//package com.sigma.quiz.presentation.api;
//
//import com.sigma.quiz.application.usecase.IAuthenticationUseCase;
//import com.sigma.quiz.domain.dto.auth.AuthenticationRequest;
//import com.sigma.quiz.domain.dto.auth.AuthenticationResponse;
//import com.sigma.quiz.domain.dto.auth.RegisterRequest;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    @Autowired
//    private final IAuthenticationUseCase service;
//
//    @PostMapping("/signup")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody RegisterRequest request
//    ) {
//        return ResponseEntity.ok(service.register(request));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest request, HttpServletRequest http
//    ) {
//        return ResponseEntity.ok(service.authenticate(request, http));
//    }
//
//
//}