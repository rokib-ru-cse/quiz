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
@PreAuthorize("hasRole('" + Constant.Role.ROLE_ADMIN + "')")
public class LevelAdminController {

    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping(AdminUrl.Level.INDEX)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView(AdminUrl.Level.INDEX);
        model.addObject(Constant.Level.LEVEL_LIST, levelUseCase.getLevels());
        return model;
    }

    @GetMapping(AdminUrl.Level.CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(AdminUrl.Level.CREATE);
        model.addObject(Constant.Level.LEVEL, new Level());
        model.addObject(Constant.ACTION_URL, AdminUrl.Level.CREATE);
        return model;
    }

    @PostMapping(AdminUrl.Level.CREATE)
    public String save(@ModelAttribute(Constant.Level.LEVEL) Level level) {
        levelUseCase.saveLevels(level);
        return AdminUrl.Level.REDIRECT_T0_INDEX;
    }

    @GetMapping(AdminUrl.Level.EDIT + "/{" + Constant.Level.LEVEL_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.Level.LEVEL_ID) Long levelId) {
        ModelAndView model = new ModelAndView(AdminUrl.Level.CREATE);
        Level level = levelUseCase.getLevel(levelId);
        model.addObject(Constant.Level.LEVEL, level);
        model.addObject(Constant.ACTION_URL, AdminUrl.Level.EDIT + "/" + levelId);
        return model;
    }

    @PostMapping(AdminUrl.Level.EDIT + "/{" + Constant.Level.LEVEL_ID + "}")
    public String edit(@PathVariable(Constant.Level.LEVEL_ID) Long levelId, @ModelAttribute(Constant.Level.LEVEL) Level level) {
        level.setId(levelId);
        levelUseCase.updateLevel(level);
        return AdminUrl.Level.REDIRECT_T0_INDEX;
    }

}