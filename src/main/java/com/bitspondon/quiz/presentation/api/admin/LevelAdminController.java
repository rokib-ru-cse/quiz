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

    @GetMapping(AdminUrl.LEVEL_INDEX)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView(AdminUrl.LEVEL_INDEX);
        Constant constants = new Constant();
        constants.setLevelList(levelUseCase.getLevels());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }


    @GetMapping(AdminUrl.LEVEL_CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(AdminUrl.LEVEL_CREATE);
        Constant constants = new Constant();
        constants.setActionUrl(AdminUrl.LEVEL_CREATE);
        model.addObject(Constant.CONSTANTS, constants);
        model.addObject(Constant.LEVEL, new Level());
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
        Constant constants = new Constant();
        constants.setActionUrl(AdminUrl.LEVEL_EDIT + "/" + levelId);
        model.addObject(Constant.CONSTANTS, constants);
        model.addObject(Constant.LEVEL, levelUseCase.getLevel(levelId));
        return model;
    }

    @PostMapping(AdminUrl.LEVEL_EDIT + "/{" + Constant.LEVEL_ID + "}")
    public String edit(@PathVariable(Constant.LEVEL_ID) Long levelId, @ModelAttribute(Constant.LEVEL) Level levelObject) {
        levelObject.setId(levelId);
        levelUseCase.updateLevel(levelObject);
        return AdminUrl.LEVEL_REDIRECT_TO_INDEX;
    }

}