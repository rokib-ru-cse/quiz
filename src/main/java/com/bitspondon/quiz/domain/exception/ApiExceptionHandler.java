package com.bitspondon.quiz.domain.exception;

import com.bitspondon.quiz.domain.ReturnReponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


//@ControllerAdvice(basePackages = "com.sigma.quiz.presentation.api.client")
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handleException(ApiException ex, WebRequest request) {
        ReturnReponse<Object> response = new ReturnReponse<>();
        response.setSucceeded(false);
        response.setValue(null);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
