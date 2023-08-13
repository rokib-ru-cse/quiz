package com.sigma.quiz.presentation.api.admin;

import com.sigma.quiz.application.usecase.*;
import com.sigma.quiz.domain.entities.Question;
import com.sigma.quiz.domain.entities.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//@RequestMapping("/api/v1/admin/question")
public class QuizAdminController {

    @Autowired
    private IQuizUseCase quizUseCase;

    @Autowired
    private IQuestionUseCase questionUseCase;
    @Autowired
    private IChapterUseCase chapterUseCase;
    @Autowired

    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping("/quiz/index")
    public String getQuizs(Model model) {
//        return quizUseCase.getQuizs();
        model.addAttribute("quizzes", quizUseCase.getQuizs());
        return "/quiz/index";
    }

    @GetMapping("/quiz/create")
    public String create(Model model) {
//        return quizUseCase.saveQuiz(subjectRequest);

        model.addAttribute("quiz", new Quiz());
        model.addAttribute("levelOptions", levelUseCase.getLevels());
        model.addAttribute("subjectOptions", subjectUseCase.getSubjects());
        model.addAttribute("chapterOptions", chapterUseCase.getChapters());
        model.addAttribute("actionUrl", "/quiz/create");

        return "/quiz/create";
    }

    /**
     * <!-- Convert a string to a date object -->
     * <p th:text="${#dates.parse('2023-07-01', 'yyyy-MM-dd')}">Date</p>
     * <p>
     * <!-- Format the date object using the 'dd-MM-yyyy' pattern -->
     * <p th:text="${#dates.format(#dates.parse('2023-07-01', 'yyyy-MM-dd'), 'dd-MM-yyyy')}">Formatted Date</p>
     */

    @PostMapping("/quiz/create")
    public String saveQuiz(@ModelAttribute("quiz") Quiz quiz) {
//        System.out.println(quiz.toString());

        quizUseCase.saveQuiz(quiz);
        return "redirect:/quiz/index";
    }

    @GetMapping("/quiz/edit/{quizId}")
    public String edit(@PathVariable("quizId") int quizId, Model model) {
//        return quizUseCase.saveQuiz(subjectRequest);
        Quiz quiz = quizUseCase.getQuiz(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("levelOptions", levelUseCase.getLevels());
        model.addAttribute("subjectOptions", subjectUseCase.getSubjects());
        model.addAttribute("chapterOptions", chapterUseCase.getChapters());
        model.addAttribute("actionUrl", "/quiz/edit/" + quizId);

        return "/quiz/create";
    }


    @PostMapping("/quiz/edit/{quizId}")
    public String editQuiz(@PathVariable("quizId") int quizId, @ModelAttribute("quiz") Quiz quiz) {
//        return quizUseCase.saveQuiz(subjectRequest);
        quiz.setId(quizId);
        quizUseCase.updateQuiz(quiz);
        return "redirect:/quiz/index";
    }

    @GetMapping("/quiz/assignquestion/{quizId}")
    public String assignQuestion(@PathVariable("quizId") int quizId, Model model) {
        Quiz quiz = quizUseCase.getQuiz(quizId);
        model.addAttribute("quiz", quiz);
        List<Question> availableQuestions = questionUseCase.getQuestions(); // Fetch available questions
        model.addAttribute("questions", availableQuestions);
        model.addAttribute("actionUrl", "/quiz/assignquestion/" + quizId);

        return "/quiz/assignquestion";
    }
    @PostMapping("/quiz/assignquestion/{quizId}")
    public String saveAssignedQuestions(@PathVariable("quizId") int quizId, @ModelAttribute("quiz") Quiz quiz) {
        // The 'questions' property of the Quiz object should now contain the selected questions
        // Update the quiz with assigned questions
        quiz.setId(quizId);
        quizUseCase.saveAssignedQuestions(quiz);
//        return "redirect:/quiz/details/" + quizId;
        return "redirect:/quiz/index";
    }
    @GetMapping("/quiz/details/{quizId}")
    public String details(@PathVariable("quizId") int quizId, Model model) {
        Quiz quiz = quizUseCase.getQuiz(quizId);
        model.addAttribute("quiz", quiz);
        return "/quiz/details";
    }
}
