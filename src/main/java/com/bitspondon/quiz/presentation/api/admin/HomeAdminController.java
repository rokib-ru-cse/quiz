package com.bitspondon.quiz.presentation.api.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class HomeAdminController {

    @GetMapping("/")
    String index() {
        return "index";
    }



    @GetMapping("/page2")
    String page2() {
        return "page2";
    }

    @GetMapping("/register")
    String registration() {
        return "registration/register";
    }
}
