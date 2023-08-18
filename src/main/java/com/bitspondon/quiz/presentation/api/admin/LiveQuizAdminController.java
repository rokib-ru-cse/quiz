package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.*;
import com.bitspondon.quiz.domain.constant.AdminUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.LiveQuiz;
import com.bitspondon.quiz.domain.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@PreAuthorize("hasRole('" + Constant.ROLE_ADMIN + "')")
public class LiveQuizAdminController {

    Logger logger = LoggerFactory.getLogger(LiveQuizAdminController.class);

    @Autowired
    private ILiveQuizUseCase liveQuizUseCase;

    @Autowired
    private IQuestionUseCase questionUseCase;
    @Autowired
    private IChapterUseCase chapterUseCase;
    @Autowired

    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;


    @GetMapping(AdminUrl.LIVE_QUIZ_INDEX)
    public ModelAndView getQuizs() {
        ModelAndView model = new ModelAndView(AdminUrl.LIVE_QUIZ_INDEX);
        Constant constants = new Constant();
        constants.setLiveQuizList(liveQuizUseCase.getLiveQuizs());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @GetMapping(AdminUrl.LIVE_QUIZ_DETAILS + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public ModelAndView details(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId) throws CustomException {
        ModelAndView model = new ModelAndView(AdminUrl.LIVE_QUIZ_DETAILS);
        model.addObject(Constant.LIVE_QUIZ, liveQuizUseCase.getLiveQuiz(quizId));
        model.addObject(Constant.CONSTANTS, new Constant());
        return model;
    }

    @GetMapping(AdminUrl.LIVE_QUIZ_CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(AdminUrl.LIVE_QUIZ_CREATE);
        Constant constants = new Constant();
        constants.setLevelList(levelUseCase.getLevels());
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setChapterList(chapterUseCase.getChapters());
        constants.setActionUrl(AdminUrl.LIVE_QUIZ_CREATE);
        model.addObject(Constant.OLD_QUIZ, new LiveQuiz());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.LIVE_QUIZ_CREATE)
    public String saveQuiz(@ModelAttribute(Constant.LIVE_QUIZ) LiveQuiz quiz) {
        liveQuizUseCase.saveLiveQuiz(quiz);
        return AdminUrl.LIVE_QUIZ_REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.LIVE_QUIZ_ENROLL + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public String enroll(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId) throws Exception {
        LiveQuiz quiz = liveQuizUseCase.enroll(quizId);
        return AdminUrl.LIVE_QUIZ_REDIRECT_TO_DETAILS + "/" + quizId;
    }


    @GetMapping(AdminUrl.LIVE_QUIZ_EDIT + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId) throws Exception {
        ModelAndView model = new ModelAndView(AdminUrl.LIVE_QUIZ_CREATE);
        Constant constants = new Constant();
        constants.setLevelList(levelUseCase.getLevels());
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setChapterList(chapterUseCase.getChapters());
        constants.setActionUrl(AdminUrl.LIVE_QUIZ_EDIT + "/" + quizId);
        model.addObject(Constant.OLD_QUIZ, liveQuizUseCase.getLiveQuiz(quizId));
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }


    @PostMapping(AdminUrl.LIVE_QUIZ_EDIT + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public String editQuiz(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId, @ModelAttribute(Constant.LIVE_QUIZ) LiveQuiz quiz) {
        quiz.setId(quizId);
        liveQuizUseCase.updateLiveQuiz(quiz);
        return AdminUrl.LIVE_QUIZ_REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.LIVE_QUIZ_ASSIGN_QUESTION + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public ModelAndView assignQuestion(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId) {
        ModelAndView model = new ModelAndView(AdminUrl.LIVE_QUIZ_ASSIGN_QUESTION);
        Constant constants = new Constant();
        constants.setQuestionList(questionUseCase.getQuestions());
        constants.setActionUrl(AdminUrl.LIVE_QUIZ_ASSIGN_QUESTION + "/" + quizId);
        model.addObject(Constant.LIVE_QUIZ, liveQuizUseCase.getLiveQuiz(quizId));
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.LIVE_QUIZ_ASSIGN_QUESTION + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public String saveAssignedQuestions(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId, @ModelAttribute(Constant.LIVE_QUIZ) LiveQuiz quiz) {
        quiz.setId(quizId);
        liveQuizUseCase.saveAssignedQuestionsToLiveQuiz(quiz);
        return AdminUrl.LIVE_QUIZ_REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.LIVE_QUIZ_START + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public ModelAndView startLiveQuiz(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId) throws Exception {
        ModelAndView model = new ModelAndView(AdminUrl.LIVE_QUIZ_START);
        Constant constants = new Constant();
        LiveQuiz liveQuiz = liveQuizUseCase.startLiveQuiz(quizId);
        constants.setQuestionList(new ArrayList<>(liveQuiz.getQuestions()));
        constants.setActionUrl(AdminUrl.LIVE_QUIZ_START + "/" + quizId);
        model.addObject(Constant.LIVE_QUIZ, liveQuiz);
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.LIVE_QUIZ_START + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public String processLiveQuizSubmission(@PathVariable(Constant.LIVE_QUIZ_ID) int quizId, @ModelAttribute(Constant.LIVE_QUIZ) LiveQuiz quiz) throws Exception {
//        LiveQuiz quiz = liveQuizUseCase.getLiveQuiz(quizId);
//        model.addAttribute("quiz", quiz);
//        List<Question> availableQuestions = questionUseCase.getQuestions(); // Fetch available questions
//        model.addAttribute("questions", availableQuestions);
//        model.addAttribute("actionUrl", "/livequiz/assignquestion/" + quizId);
//        LiveQuiz quiz = liveQuizUseCase.enroll(quizId);

        return "redirect:/livequiz/index";
    }
}
