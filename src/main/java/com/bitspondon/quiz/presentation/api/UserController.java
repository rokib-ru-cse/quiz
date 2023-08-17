package com.bitspondon.quiz.presentation.api;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
//@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/userdetails")
    public String getUserDetails(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // Populate the model attributes with user details for the HTML template
            model.addAttribute("email", userDetails.getUsername());
            model.addAttribute("authorities", userDetails.getAuthorities());
            return "/auth/userdetails"; // Return the appropriate Thymeleaf template name
        } else {
            return "redirect:/login"; // Redirect to the login page if not authenticated
        }
    }
}
