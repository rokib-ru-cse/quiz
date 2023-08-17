package com.bitspondon.quiz.presentation.api;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model, Exception exception) {
        // Get the status code and error message from the request attributes
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String msg = exception.getMessage();
        int statusCode = status instanceof Integer ? (Integer) status : HttpStatus.INTERNAL_SERVER_ERROR.value();
        String errorMsg = errorMessage instanceof String ? (String) errorMessage : "An error occurred.";
        if (errorMessage.equals("Forbidden")) {
            errorMsg = "You donot have access to this route.";
        }
        if (errorMsg.length() == 0) {
            errorMsg = msg;
        }
        // Pass the status code and error message to the Thymeleaf template
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("errorMessage", errorMsg);

        return "/auth/error";
    }

}