package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.constant.AdminUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.Subject;
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
public class SubjectAdminController {

    @Autowired
    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;


    @GetMapping(AdminUrl.SUBJECT_INDEX)
    public ModelAndView getSubjects() {
        ModelAndView model = new ModelAndView(AdminUrl.SUBJECT_INDEX);
        Constant constants = new Constant();
        constants.setSubjectList(subjectUseCase.getSubjects());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @GetMapping(AdminUrl.SUBJECT_CREATE)
    public ModelAndView create() {
        Constant constants = new Constant();
        ModelAndView model = new ModelAndView(AdminUrl.SUBJECT_CREATE);
        constants.setLevelList(levelUseCase.getLevels());
        constants.setActionUrl(AdminUrl.SUBJECT_CREATE);
        model.addObject(Constant.SUBJECT, new Subject());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }


    @PostMapping(AdminUrl.SUBJECT_CREATE)
    public String saveSubject(@ModelAttribute(Constant.SUBJECT) Subject subject) {
        subjectUseCase.saveSubject(subject);
        return AdminUrl.SUBJECT_REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.SUBJECT_EDIT + "/{" + Constant.SUBJECT_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.SUBJECT_ID) Long subjectId) {
        Constant constants = new Constant();
        ModelAndView model = new ModelAndView(AdminUrl.SUBJECT_CREATE);
        Subject subject = subjectUseCase.getSubject(subjectId);
        constants.setLevelList(levelUseCase.getLevels());
        constants.setActionUrl(AdminUrl.SUBJECT_EDIT + "/" + subjectId);
        model.addObject(Constant.SUBJECT, subject);
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.SUBJECT_EDIT + "/{" + Constant.SUBJECT_ID + "}")
    public String edit(@PathVariable(Constant.SUBJECT_ID) Long subjectId, @ModelAttribute(Constant.SUBJECT) Subject subject) {
        subject.setId(subjectId);
        subjectUseCase.updateSubject(subject);
        return AdminUrl.SUBJECT_REDIRECT_TO_INDEX;
    }

}
