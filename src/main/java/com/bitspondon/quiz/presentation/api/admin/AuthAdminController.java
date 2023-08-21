package com.bitspondon.quiz.presentation.api.admin;

//import com.sigma.quiz.application.usecase.IAuthenticationUseCase;

import com.bitspondon.quiz.domain.dto.auth.RegisterRequest;
import com.bitspondon.quiz.application.usecase.IAuthenticationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthAdminController {

    @Autowired
    private IAuthenticationUseCase authenticationUseCase;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username,@RequestParam("password") String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return "redirect:/";
//        } else {
//            return "redirect:/adminlogin?error";
//        }
//    }

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }


    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new RegisterRequest());
        model.addAttribute("actionUrl", "/register");

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") RegisterRequest request, Model model) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            model.addAttribute("errormessage", "Password doesn't matched");
            return "redirect:/register?error=true";
        }
        authenticationUseCase.createUser(request);
        return "redirect:/";
    }
}
