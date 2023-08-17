package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.IChapterUseCase;
import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.bitspondon.quiz.application.usecase.IQuestionUseCase;
import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/api/v1/admin/question")
public class QuestionAdminController {

    @Autowired
    private IQuestionUseCase questionUseCase;
    @Autowired
    private IChapterUseCase chapterUseCase;
    @Autowired

    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping("/question/index")
    public String getQuestions(Model model) {
//        return questionUseCase.getQuestions();
        model.addAttribute("questions", questionUseCase.getQuestions());
        return "/question/index";
    }

    @GetMapping("/question/create")
    public String create(Model model) {

        model.addAttribute("question", new Question());
        model.addAttribute("levelOptions", levelUseCase.getLevels());
        model.addAttribute("subjectOptions", subjectUseCase.getSubjects());
        model.addAttribute("chapterOptions", chapterUseCase.getChapters());
        model.addAttribute("options", new ArrayList<OptionDTO>());
        model.addAttribute("actionUrl", "/question/create");
        return "/question/create";
    }

    @PostMapping("/question/create")
    public String saveQuestion(@ModelAttribute("question") Question question) {
        questionUseCase.saveQuestion(question);
        return "redirect:/question/index";
    }

    @GetMapping("/question/edit/{questionId}")
    public String edit(@PathVariable("questionId") Long questionId, Model model) {
        Question question = questionUseCase.getQuestion(questionId);
        model.addAttribute("question", question);
        model.addAttribute("levelOptions", levelUseCase.getLevels());
        model.addAttribute("subjectOptions", subjectUseCase.getSubjects());
        model.addAttribute("chapterOptions", chapterUseCase.getChapters());
        model.addAttribute("options", question.getOptionList());
        model.addAttribute("actionUrl", "/question/edit/" + questionId);
        return "/question/create";
    }


    @PostMapping("/question/edit/{questionId}")
    public String editQuestion(@PathVariable("questionId") Long questionId, @ModelAttribute("question") Question question) {
        question.setId(questionId);
        questionUseCase.updateQuestion(question);
        return "redirect:/question/index";
    }
}
