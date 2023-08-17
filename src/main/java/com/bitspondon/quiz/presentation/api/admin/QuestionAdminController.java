//package com.bitspondon.quiz.presentation.api.admin;
//
//import com.bitspondon.quiz.application.usecase.IChapterUseCase;
//import com.bitspondon.quiz.application.usecase.ILevelUseCase;
//import com.bitspondon.quiz.application.usecase.IQuestionUseCase;
//import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
//import com.bitspondon.quiz.domain.constant.AdminUrl;
//import com.bitspondon.quiz.domain.constant.Constant;
//import com.bitspondon.quiz.domain.dto.question.OptionDTO;
//import com.bitspondon.quiz.domain.entities.Question;
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
//import java.util.ArrayList;
//
//@Controller
//@PreAuthorize("hasRole('" + Constant.Role.ROLE_ADMIN + "')")
//public class QuestionAdminController {
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
//    @GetMapping(AdminUrl.Question.INDEX)
//    public String getQuestions(Model model) {
//        model.addAttribute(Constant.Question.QUESTION_LIST, questionUseCase.getQuestions());
//        return AdminUrl.Question.INDEX;
//    }
//
//    @GetMapping(AdminUrl.Question.CREATE)
//    public ModelAndView create() {
//        ModelAndView model = new ModelAndView(AdminUrl.Question.CREATE);
//        model.addObject(Constant.Question.QUESTION, new Question());
//        model.addObject(Constant.Level.LEVEL_LIST, levelUseCase.getLevels());
//        model.addObject(Constant.Subject.SUBJECT_LIST, subjectUseCase.getSubjects());
//        model.addObject(Constant.Chapter.CHAPTER_LIST, chapterUseCase.getChapters());
//        model.addObject(Constant.Question.QUESTION_OPTIONS, new ArrayList<OptionDTO>());
//        model.addObject(Constant.ACTION_URL, AdminUrl.Question.CREATE);
//        return model;
//    }
//
//    @PostMapping(AdminUrl.Question.CREATE)
//    public String saveQuestion(@ModelAttribute(Constant.Question.QUESTION) Question question) {
//        questionUseCase.saveQuestion(question);
//        return AdminUrl.Question.REDIRECT_TO_INDEX;
//    }
//
//    @GetMapping(AdminUrl.Question.EDIT + "/{" + Constant.Question.QUESTION_ID + "}")
//    public ModelAndView edit(@PathVariable(Constant.Question.QUESTION_ID) Long questionId) {
//        ModelAndView model = new ModelAndView(AdminUrl.Question.CREATE);
//        Question question = questionUseCase.getQuestion(questionId);
//        model.addObject(Constant.Question.QUESTION, question);
//        model.addObject(Constant.Level.LEVEL_LIST, levelUseCase.getLevels());
//        model.addObject(Constant.Subject.SUBJECT_LIST, subjectUseCase.getSubjects());
//        model.addObject(Constant.Chapter.CHAPTER_LIST, chapterUseCase.getChapters());
//        model.addObject(Constant.Question.QUESTION_OPTIONS, question.getOptionList());
//        model.addObject(Constant.ACTION_URL, AdminUrl.Question.EDIT + "/" + questionId);
//        return model;
//    }
//
//
//    @PostMapping(AdminUrl.Question.EDIT + "/{" + Constant.Question.QUESTION_ID + "}")
//    public String editQuestion(@PathVariable(Constant.Question.QUESTION_ID) Long questionId, @ModelAttribute(Constant.Question.QUESTION) Question question) {
//        question.setId(questionId);
//        questionUseCase.updateQuestion(question);
//        return AdminUrl.Question.REDIRECT_TO_INDEX;
//    }
//}
