package com.bitspondon.quiz.domain.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice(basePackages = "com.sigma.quiz.presentation.api.admin")
@ControllerAdvice
public class AdminExceptionHandler {

    @ExceptionHandler(AdminException.class)
    public ModelAndView handleAdminException(AdminException ex, Model model) {
        ModelAndView modelAndView = new ModelAndView("/auth/error"); // Error view template for admin
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("statusCode", 500);
        return modelAndView;
    }
}
