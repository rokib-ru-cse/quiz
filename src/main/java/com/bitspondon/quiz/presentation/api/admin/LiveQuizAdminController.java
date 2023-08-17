//package com.bitspondon.quiz.presentation.api.admin;
//
//import com.bitspondon.quiz.application.usecase.*;
//import com.bitspondon.quiz.domain.constant.AdminUrl;
//import com.bitspondon.quiz.domain.constant.Constant;
//import com.bitspondon.quiz.domain.entities.LiveQuiz;
//import com.bitspondon.quiz.domain.entities.Question;
//import com.bitspondon.quiz.domain.exception.CustomException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//@Controller
//@PreAuthorize("hasRole('" + Constant.Role.ROLE_ADMIN + "')")
//public class LiveQuizAdminController {
//
//    Logger logger = LoggerFactory.getLogger(LiveQuizAdminController.class);
//
//    @Autowired
//    private ILiveQuizUseCase liveQuizUseCase;
//
//    @Autowired
//    private IQuestionUseCase questionUseCase;
//    @Autowired
//    private IChapterUseCase chapterUseCase;
//    @Autowired
//
//    private ISubjectUseCase subjectUseCase;
//    @Autowired
//    private ILevelUseCase levelUseCase;
//
//
//    @GetMapping(AdminUrl.LiveQuiz.INDEX)
//    public ModelAndView getQuizs() {
//        ModelAndView model = new ModelAndView(AdminUrl.LiveQuiz.INDEX);
//        model.addObject(Constant.LiveQuiz.LIVE_QUIZ_LIST, liveQuizUseCase.getLiveQuizs());
//        return model;
//    }
//
//    @GetMapping(AdminUrl.LiveQuiz.DETAILS + "/{" + Constant.LiveQuiz.LIVE_QUIZ_ID + "}")
//    public ModelAndView details(@PathVariable(Constant.LiveQuiz.LIVE_QUIZ_ID) Long quizId) throws CustomException {
//        ModelAndView model = new ModelAndView(AdminUrl.LiveQuiz.DETAILS);
//        model.addObject(Constant.LiveQuiz.LIVE_QUIZ, liveQuizUseCase.getLiveQuiz(quizId));
//        return model;
//    }
//
//    @GetMapping(AdminUrl.LiveQuiz.CREATE)
//    public ModelAndView create() {
//        ModelAndView model = new ModelAndView(AdminUrl.LiveQuiz.CREATE);
//        model.addObject(Constant.LiveQuiz.LIVE_QUIZ, new LiveQuiz());
//        model.addObject(Constant.Level.LEVEL_LIST, levelUseCase.getLevels());
//        model.addObject(Constant.Subject.SUBJECT_LIST, subjectUseCase.getSubjects());
//        model.addObject(Constant.Chapter.CHAPTER_LIST, chapterUseCase.getChapters());
//        model.addObject(Constant.ACTION_URL, AdminUrl.LiveQuiz.CREATE);
//        return model;
//    }
//
//    @PostMapping(AdminUrl.LiveQuiz.CREATE)
//    public String saveQuiz(@ModelAttribute(Constant.LiveQuiz.LIVE_QUIZ) LiveQuiz quiz) {
//        liveQuizUseCase.saveLiveQuiz(quiz);
//        return AdminUrl.LiveQuiz.REDIRECT_TO_INDEX;
//    }
//
//    @GetMapping(AdminUrl.LiveQuiz.ENROLL + "/{" + Constant.LiveQuiz.LIVE_QUIZ_ID + "}")
//    public String enroll(@PathVariable(Constant.LiveQuiz.LIVE_QUIZ_ID) Long quizId) throws Exception {
//        LiveQuiz quiz = liveQuizUseCase.enroll(quizId);
//        return AdminUrl.LiveQuiz.REDIRECT_TO_DETAILS + "/" + quizId;
//    }
//
//
//    @GetMapping(AdminUrl.LiveQuiz.EDIT + "/{" + Constant.LiveQuiz.LIVE_QUIZ_ID + "}")
//    public ModelAndView edit(@PathVariable(Constant.LiveQuiz.LIVE_QUIZ_ID) Long quizId) throws Exception {
//        ModelAndView model = new ModelAndView(AdminUrl.LiveQuiz.CREATE);
//        model.addObject(Constant.LiveQuiz.LIVE_QUIZ, liveQuizUseCase.getLiveQuiz(quizId));
//        model.addObject(Constant.Level.LEVEL_LIST, levelUseCase.getLevels());
//        model.addObject(Constant.Subject.SUBJECT_LIST, subjectUseCase.getSubjects());
//        model.addObject(Constant.Chapter.CHAPTER_LIST, chapterUseCase.getChapters());
//        model.addObject(Constant.ACTION_URL, AdminUrl.LiveQuiz.CREATE + "/" + quizId);
//        return model;
//    }
//
//
//    @PostMapping(AdminUrl.LiveQuiz.EDIT + "/{" + Constant.LiveQuiz.LIVE_QUIZ_ID + "}")
//    public String editQuiz(@PathVariable(Constant.LiveQuiz.LIVE_QUIZ_ID) Long quizId, @ModelAttribute(Constant.LiveQuiz.LIVE_QUIZ) LiveQuiz quiz) {
//        quiz.setId(quizId);
//        liveQuizUseCase.updateLiveQuiz(quiz);
//        return AdminUrl.LiveQuiz.REDIRECT_TO_INDEX;
//    }
//
//    @GetMapping(AdminUrl.LiveQuiz.ASSIGN_QUESTION + "/{" + Constant.LiveQuiz.LIVE_QUIZ_ID + "}")
//    public ModelAndView assignQuestion(@PathVariable(Constant.LiveQuiz.LIVE_QUIZ_ID) Long quizId) {
//        ModelAndView model = new ModelAndView(AdminUrl.LiveQuiz.ASSIGN_QUESTION);
//        model.addObject(Constant.LiveQuiz.LIVE_QUIZ, liveQuizUseCase.getLiveQuiz(quizId));
//        model.addObject(Constant.Question.QUESTION_LIST, questionUseCase.getQuestions());
//        model.addObject(Constant.ACTION_URL, AdminUrl.LiveQuiz.ASSIGN_QUESTION + "/" + quizId);
//        return model;
//    }
//
//    @PostMapping(AdminUrl.LiveQuiz.ASSIGN_QUESTION + "/{" + Constant.LiveQuiz.LIVE_QUIZ_ID + "}")
//    public String saveAssignedQuestions(@PathVariable(Constant.LiveQuiz.LIVE_QUIZ_ID) Long quizId, @ModelAttribute(Constant.LiveQuiz.LIVE_QUIZ) LiveQuiz quiz) {
//        quiz.setId(quizId);
//        liveQuizUseCase.saveAssignedQuestionsToLiveQuiz(quiz);
//        return AdminUrl.LiveQuiz.REDIRECT_TO_INDEX;
//    }
//
//    @GetMapping("/livequiz/startlivequiz/{quizId}")
//    public String startLiveQuiz(@PathVariable("quizId") Long quizId, Model model) throws Exception {
//
//        LiveQuiz quiz = liveQuizUseCase.startLiveQuiz(quizId);
//        model.addAttribute("quiz", quiz);
//        List<Question> availableQuestions = questionUseCase.getQuestions(); // Fetch available questions
//        model.addAttribute("questions", availableQuestions);
//        model.addAttribute("actionUrl", "/livequiz/startlivequiz/" + quizId);
//        return "/livequiz/startlivequiz";
//    }
//
//    @PostMapping("/livequiz/startlivequiz/{quizId}")
//    public String processLiveQuizSubmission(@PathVariable("quizId") int quizId, @ModelAttribute("quiz") LiveQuiz quiz) throws Exception {
////        LiveQuiz quiz = liveQuizUseCase.getLiveQuiz(quizId);
////        model.addAttribute("quiz", quiz);
////        List<Question> availableQuestions = questionUseCase.getQuestions(); // Fetch available questions
////        model.addAttribute("questions", availableQuestions);
////        model.addAttribute("actionUrl", "/livequiz/assignquestion/" + quizId);
////        LiveQuiz quiz = liveQuizUseCase.enroll(quizId);
//
//        return "redirect:/livequiz/index";
//    }
//}
