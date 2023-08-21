package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.*;
import com.bitspondon.quiz.domain.constant.AdminUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.OldQuiz;
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
public class OldQuizAdminController {

    @Autowired
    private IOldQuizUseCase oldQuizUseCase;

    @Autowired
    private IQuestionUseCase questionUseCase;
    @Autowired
    private IChapterUseCase chapterUseCase;
    @Autowired

    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping(AdminUrl.OLD_QUIZ_INDEX)
    public ModelAndView getQuizs() {
        ModelAndView model = new ModelAndView(AdminUrl.OLD_QUIZ_INDEX);
        Constant constants = new Constant();
        constants.setOldQuizList(oldQuizUseCase.getOldQuizzes());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @GetMapping(AdminUrl.OLD_QUIZ_DETAILS + "/{" + Constant.OLD_QUIZ_ID + "}")
    public ModelAndView details(@PathVariable(Constant.OLD_QUIZ_ID) Long quizId) {
        ModelAndView model = new ModelAndView(AdminUrl.OLD_QUIZ_DETAILS);
        Constant constants = new Constant();
        model.addObject(Constant.OLD_QUIZ, oldQuizUseCase.getOldQuiz(quizId));
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @GetMapping(AdminUrl.OLD_QUIZ_CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(AdminUrl.OLD_QUIZ_CREATE);
        Constant constants = new Constant();
        constants.setLevelList(levelUseCase.getLevels());
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setChapterList(chapterUseCase.getChapters());
        constants.setActionUrl(AdminUrl.OLD_QUIZ_CREATE);
        model.addObject(Constant.OLD_QUIZ, new OldQuiz());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.OLD_QUIZ_CREATE)
    public String saveQuiz(@ModelAttribute(Constant.OLD_QUIZ) OldQuiz quiz) {
        oldQuizUseCase.saveOldQuiz(quiz);
        return AdminUrl.OLD_QUIZ_REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.OLD_QUIZ_EDIT + "/{" + Constant.OLD_QUIZ_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.OLD_QUIZ_ID) Long quizId) {
        ModelAndView model = new ModelAndView(AdminUrl.OLD_QUIZ_CREATE);
        Constant constants = new Constant();
        constants.setLevelList(levelUseCase.getLevels());
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setChapterList(chapterUseCase.getChapters());
        constants.setActionUrl(AdminUrl.OLD_QUIZ_EDIT + "/" + quizId);
        model.addObject(Constant.OLD_QUIZ, oldQuizUseCase.getOldQuiz(quizId));
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }


    @PostMapping(AdminUrl.OLD_QUIZ_EDIT + "/{" + Constant.OLD_QUIZ_ID + "}")
    public String editQuiz(@PathVariable(Constant.OLD_QUIZ_ID) Long quizId, @ModelAttribute(Constant.OLD_QUIZ) OldQuiz quiz) {
        quiz.setId(quizId);
        oldQuizUseCase.updateOldQuiz(quiz);
        return AdminUrl.OLD_QUIZ_REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.OLD_QUIZ_ASSIGN_QUESTION + "/{" + Constant.OLD_QUIZ_ID + "}")
    public ModelAndView assignQuestion(@PathVariable(Constant.OLD_QUIZ_ID) Long quizId) {
        ModelAndView model = new ModelAndView(AdminUrl.OLD_QUIZ_ASSIGN_QUESTION);
        Constant constants = new Constant();
        constants.setQuestionList(questionUseCase.getQuestions());
        constants.setActionUrl(AdminUrl.OLD_QUIZ_ASSIGN_QUESTION + "/" + quizId);
        model.addObject(Constant.OLD_QUIZ, oldQuizUseCase.getOldQuiz(quizId));
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.OLD_QUIZ_ASSIGN_QUESTION + "/{" + Constant.OLD_QUIZ_ID + "}")
    public String saveAssignedQuestions(@PathVariable(Constant.OLD_QUIZ_ID) Long quizId, @ModelAttribute(Constant.OLD_QUIZ) OldQuiz quiz) {
        quiz.setId(quizId);
        oldQuizUseCase.saveAssignedQuestionsToOldQuiz(quiz);
        return AdminUrl.OLD_QUIZ_REDIRECT_TO_INDEX;
    }


}
