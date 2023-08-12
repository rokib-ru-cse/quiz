package com.sigma.quiz.presentation.api.admin;

import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/api/v1/admin/level")
public class LevelAdminController {

    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping("/level/index")
    public String index(Model model) {
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
//        levelUseCase.saveLevels(level);
        return "redirect:/level/index";
    }

    @GetMapping("/level/edit/{levelId}")
//    public String edit(@RequestParam("levelId") int levelId) {
    public String edit(@PathVariable("levelId") int levelId, Model model) {
        System.out.println(levelId);
        Level level = levelUseCase.getLevel(levelId);
        model.addAttribute("level", level);
        model.addAttribute("actionUrl", "/level/edit/" + levelId);
        return "/level/create";
    }

    @PostMapping("/level/edit/{levelId}")
    public String edit(@PathVariable("levelId") int levelId,@ModelAttribute("level") Level level) {
//        System.out.println(level.toString());
        level.setId(levelId);
        levelUseCase.updateLevel(level);
        return "redirect:/level/index";
    }

}