package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.constant.AdminUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.Level;
import com.bitspondon.quiz.domain.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@PreAuthorize("hasRole('" + Constant.Role.ROLE_ADMIN + "')")
public class SubjectAdminController {

    @Autowired
    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;


    @GetMapping(AdminUrl.Subject.INDEX)
    public ModelAndView getSubjects() {
        ModelAndView model = new ModelAndView(AdminUrl.Subject.INDEX);
        model.addObject(Constant.Subject.SUBJECT_LIST, subjectUseCase.getSubjects());
        return model;
    }

    @GetMapping(AdminUrl.Subject.CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(AdminUrl.Subject.CREATE);
        List<Level> levelList = levelUseCase.getLevels();
        model.addObject(Constant.Subject.SUBJECT, new Subject());
        model.addObject(Constant.Level.LEVEL_LIST, levelList);
        model.addObject(Constant.ACTION_URL, AdminUrl.Subject.CREATE);
        return model;
    }


    @PostMapping(AdminUrl.Subject.CREATE)
    public String saveSubject(@ModelAttribute(Constant.Subject.SUBJECT) Subject subject) {
        subjectUseCase.saveSubject(subject);
        return AdminUrl.Subject.REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.Subject.EDIT + "/{" + Constant.Subject.SUBJECT_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.Subject.SUBJECT_ID) Long subjectId) {
        ModelAndView model = new ModelAndView(AdminUrl.Subject.CREATE);
        Subject subject = subjectUseCase.getSubject(subjectId);
        List<Level> levelList = levelUseCase.getLevels();

        model.addObject(Constant.Subject.SUBJECT, subject);
        model.addObject(Constant.Level.LEVEL_LIST, levelList);
        model.addObject(Constant.ACTION_URL, AdminUrl.Subject.EDIT + "/" + subjectId);
        return model;
    }

    @PostMapping(AdminUrl.Subject.EDIT + "/{" + Constant.Subject.SUBJECT_ID + "}")
    public String edit(@PathVariable(Constant.Subject.SUBJECT_ID) Long subjectId, @ModelAttribute(Constant.Subject.SUBJECT) Subject subject) {
        subject.setId(subjectId);
        subjectUseCase.updateSubject(subject);
        return AdminUrl.Subject.REDIRECT_TO_INDEX;
    }

}
