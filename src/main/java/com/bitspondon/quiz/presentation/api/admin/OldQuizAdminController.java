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
@PreAuthorize("hasRole('" + Constant.Role.ROLE_ADMIN + "')")
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

    @GetMapping(AdminUrl.OldQuiz.INDEX)
    public ModelAndView getQuizs() {
        ModelAndView model = new ModelAndView();
        model.addObject(Constant.OldQuiz.OLD_QUIZ_LIST, oldQuizUseCase.getQuizs());
        return model;
    }

    @GetMapping(AdminUrl.OldQuiz.CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(AdminUrl.OldQuiz.CREATE);
        model.addObject(Constant.OldQuiz.OLD_QUIZ, new OldQuiz());
        model.addObject(Constant.Level.LEVEL_LIST, levelUseCase.getLevels());
        model.addObject(Constant.Subject.SUBJECT_LIST, subjectUseCase.getSubjects());
        model.addObject(Constant.Chapter.CHAPTER_LIST, chapterUseCase.getChapters());
        model.addObject(Constant.ACTION_URL, AdminUrl.OldQuiz.CREATE);
        return model;
    }

    @PostMapping(AdminUrl.OldQuiz.CREATE)
    public String saveQuiz(@ModelAttribute(Constant.OldQuiz.OLD_QUIZ) OldQuiz quiz) {
        oldQuizUseCase.saveOldQuiz(quiz);
        return AdminUrl.OldQuiz.REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.OldQuiz.EDIT + "/{" + Constant.OldQuiz.OLD_QUIZ_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.OldQuiz.OLD_QUIZ_ID) Long quizId) {
        ModelAndView model = new ModelAndView(AdminUrl.OldQuiz.CREATE);
        OldQuiz quiz = oldQuizUseCase.getQuiz(quizId);
        model.addObject(Constant.OldQuiz.OLD_QUIZ, quiz);
        model.addObject(Constant.Level.LEVEL_LIST, levelUseCase.getLevels());
        model.addObject(Constant.Subject.SUBJECT_LIST, subjectUseCase.getSubjects());
        model.addObject(Constant.Chapter.CHAPTER_LIST, chapterUseCase.getChapters());
        model.addObject(Constant.ACTION_URL, AdminUrl.OldQuiz.EDIT + "/" + quizId);

        return model;
    }


    @PostMapping(AdminUrl.OldQuiz.EDIT + "/{" + Constant.OldQuiz.OLD_QUIZ_ID + "}")
    public String editQuiz(@PathVariable(Constant.OldQuiz.OLD_QUIZ_ID) Long quizId, @ModelAttribute(Constant.OldQuiz.OLD_QUIZ) OldQuiz quiz) {
        quiz.setId(quizId);
        oldQuizUseCase.updateQuiz(quiz);
        return AdminUrl.OldQuiz.REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.OldQuiz.ASSIGN_QUESTION + "/{" + Constant.OldQuiz.OLD_QUIZ_ID + "}")
    public ModelAndView assignQuestion(@PathVariable(Constant.OldQuiz.OLD_QUIZ_ID) Long quizId) {
        ModelAndView model = new ModelAndView(AdminUrl.OldQuiz.ASSIGN_QUESTION);
        model.addObject(Constant.OldQuiz.OLD_QUIZ, oldQuizUseCase.getQuiz(quizId));
        model.addObject(Constant.Question.QUESTION_LIST, questionUseCase.getQuestions());
        model.addObject(Constant.ACTION_URL, AdminUrl.OldQuiz.ASSIGN_QUESTION + "/" + quizId);
        return model;
    }

    @PostMapping(AdminUrl.OldQuiz.ASSIGN_QUESTION + "/{" + Constant.OldQuiz.OLD_QUIZ_ID + "}")
    public String saveAssignedQuestions(@PathVariable(Constant.OldQuiz.OLD_QUIZ_ID) Long quizId, @ModelAttribute(Constant.OldQuiz.OLD_QUIZ) OldQuiz quiz) {
        quiz.setId(quizId);
        oldQuizUseCase.saveAssignedQuestions(quiz);
        return AdminUrl.OldQuiz.REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.OldQuiz.DETAILS + "/{" + Constant.OldQuiz.OLD_QUIZ_ID + "}")
    public ModelAndView details(@PathVariable(Constant.OldQuiz.OLD_QUIZ_ID) Long quizId) {
        ModelAndView model = new ModelAndView(AdminUrl.OldQuiz.DETAILS);
        OldQuiz quiz = oldQuizUseCase.getQuiz(quizId);
        model.addObject(Constant.OldQuiz.OLD_QUIZ, quiz);
        return model;
    }
}
