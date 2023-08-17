package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.domain.constant.AdminUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("hasRole('" + Constant.ROLE_ADMIN + "')")
public class LevelAdminController {

    @Autowired
    private ILevelUseCase levelUseCase;

//    @GetMapping(AdminUrl.LEVEL_INDEX)
//    public ModelAndView index() {
//        ModelAndView model = new ModelAndView(AdminUrl.LEVEL_INDEX);
//        model.addObject("constants", new Constant()); // Add the constant class to the model
////        model.addObject(Constant.LEVEL_LIST, levelUseCase.getLevels());
//        return model;
//    }

    @GetMapping(AdminUrl.LEVEL_INDEX)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView(AdminUrl.LEVEL_INDEX);
        Constant constants = new Constant();
        constants.LEVEL_LIST = levelUseCase.getLevels();
        model.addObject(Constant.CONSTANT, constants);
        model.addObject(Constant.ADMIN_URL, new AdminUrl());
        return model;
    }


    @GetMapping(AdminUrl.LEVEL_CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(AdminUrl.LEVEL_CREATE);
        model.addObject(Constant.LEVEL, new Level());
        model.addObject(Constant.ACTION_URL, AdminUrl.LEVEL_CREATE);
        return model;
    }

    @PostMapping(AdminUrl.LEVEL_CREATE)
    public String save(@ModelAttribute(Constant.LEVEL) Level level) {
        levelUseCase.saveLevels(level);
        return AdminUrl.LEVEL_REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.LEVEL_EDIT + "/{" + Constant.LEVEL_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.LEVEL_ID) Long levelId) {
        ModelAndView model = new ModelAndView(AdminUrl.LEVEL_CREATE);
        Level level = levelUseCase.getLevel(levelId);
        model.addObject(Constant.LEVEL, level);
        model.addObject(Constant.ACTION_URL, AdminUrl.LEVEL_EDIT + "/" + levelId);
        return model;
    }

    @PostMapping(AdminUrl.LEVEL_EDIT + "/{" + Constant.LEVEL_ID + "}")
    public String edit(@PathVariable(Constant.LEVEL_ID) Long levelId, @ModelAttribute(Constant.LEVEL) Level level) {
        level.setId(levelId);
        levelUseCase.updateLevel(level);
        return AdminUrl.LEVEL_REDIRECT_TO_INDEX;
    }

}