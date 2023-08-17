package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/api/v1/admin/level")
public class LevelAdminController {

    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping("/level/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                // You have the "ADMIN" role
            }
        }
        model.addAttribute("levels", levelUseCase.getLevels());
        return "/level/index";
    }

    @GetMapping("/level/create")
    public String create(Model model) {
        model.addAttribute("level", new Level());
        model.addAttribute("actionUrl", "/level/create");
        return "/level/create";
    }

    @PostMapping("/level/create")
    public String save(@ModelAttribute("level") Level level) {
        System.out.println(level.toString());
        levelUseCase.saveLevels(level);
        return "redirect:/level/index";
    }

    @GetMapping("/level/edit/{levelId}")
//    public String edit(@RequestParam("levelId") int levelId) {
    public String edit(@PathVariable("levelId") Long levelId, Model model) {
        System.out.println(levelId);
        Level level = levelUseCase.getLevel(levelId);
        model.addAttribute("level", level);
        model.addAttribute("actionUrl", "/level/edit/" + levelId);
        return "/level/create";
    }

    @PostMapping("/level/edit/{levelId}")
    public String edit(@PathVariable("levelId") Long levelId,@ModelAttribute("level") Level level) {
//        System.out.println(level.toString());
        level.setId(levelId);
        levelUseCase.updateLevel(level);
        return "redirect:/level/index";
    }

}