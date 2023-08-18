package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.IChapterUseCase;
import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.application.usecase.IQuestionUseCase;
import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.constant.AdminUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@PreAuthorize("hasRole('" + Constant.ROLE_ADMIN + "')")
public class QuestionAdminController {

    @Autowired
    private IQuestionUseCase questionUseCase;
    @Autowired
    private IChapterUseCase chapterUseCase;
    @Autowired

    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping(AdminUrl.QUESTION_INDEX)
    public ModelAndView getQuestions() {
        ModelAndView model = new ModelAndView(AdminUrl.QUESTION_INDEX);
        Constant constants = new Constant();
        constants.setQuestionList(questionUseCase.getQuestions());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @GetMapping(AdminUrl.QUESTION_CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(AdminUrl.QUESTION_CREATE);
        Constant constants = new Constant();
        constants.setLevelList(levelUseCase.getLevels());
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setChapterList(chapterUseCase.getChapters());
        constants.setActionUrl(AdminUrl.QUESTION_CREATE);
        model.addObject(Constant.QUESTION, new Question());
        model.addObject(Constant.QUESTION_OPTIONS, new ArrayList<OptionDTO>());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.QUESTION_CREATE)
    public String saveQuestion(@ModelAttribute(Constant.QUESTION) Question question) {
        questionUseCase.saveQuestion(question);
        return AdminUrl.QUESTION_REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.QUESTION_EDIT + "/{" + Constant.QUESTION_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.QUESTION_ID) Long questionId) {
        ModelAndView model = new ModelAndView(AdminUrl.QUESTION_CREATE);
        Question question = questionUseCase.getQuestion(questionId);
        Constant constants = new Constant();
        constants.setLevelList(levelUseCase.getLevels());
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setChapterList(chapterUseCase.getChapters());
        constants.setActionUrl(AdminUrl.QUESTION_EDIT + "/" + questionId);
        model.addObject(Constant.QUESTION, question);
        model.addObject(Constant.QUESTION_OPTIONS, question.getOptionList());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }


    @PostMapping(AdminUrl.QUESTION_EDIT + "/{" + Constant.QUESTION_ID + "}")
    public String editQuestion(@PathVariable(Constant.QUESTION_ID) Long questionId, @ModelAttribute(Constant.QUESTION) Question question) {
        question.setId(questionId);
        questionUseCase.updateQuestion(question);
        return AdminUrl.QUESTION_REDIRECT_TO_INDEX;
    }
}
