package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.*;
import com.bitspondon.quiz.domain.entities.OldQuiz;
import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/api/v1/admin/question")
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

    @GetMapping("/quiz/index")
    public String getQuizs(Model model) {
//        return oldQuizUseCase.getQuizs();
        model.addAttribute("quizzes", oldQuizUseCase.getQuizs());
        return "/quiz/index";
    }

    @GetMapping("/quiz/create")
    public String create(Model model) {
//        return oldQuizUseCase.saveQuiz(subjectRequest);

        model.addAttribute("quiz", new OldQuiz());
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
    public String saveQuiz(@ModelAttribute("quiz") OldQuiz quiz) {
//        System.out.println(quiz.toString());

        oldQuizUseCase.saveOldQuiz(quiz);
        return "redirect:/quiz/index";
    }

    @GetMapping("/quiz/edit/{quizId}")
    public String edit(@PathVariable("quizId") Long quizId, Model model) {
//        return oldQuizUseCase.saveQuiz(subjectRequest);
        OldQuiz quiz = oldQuizUseCase.getQuiz(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("levelOptions", levelUseCase.getLevels());
        model.addAttribute("subjectOptions", subjectUseCase.getSubjects());
        model.addAttribute("chapterOptions", chapterUseCase.getChapters());
        model.addAttribute("actionUrl", "/quiz/edit/" + quizId);

        return "/quiz/create";
    }


    @PostMapping("/quiz/edit/{quizId}")
    public String editQuiz(@PathVariable("quizId") Long quizId, @ModelAttribute("quiz") OldQuiz quiz) {
//        return oldQuizUseCase.saveQuiz(subjectRequest);
        quiz.setId(quizId);
        oldQuizUseCase.updateQuiz(quiz);
        return "redirect:/quiz/index";
    }

    @GetMapping("/quiz/assignquestion/{quizId}")
    public String assignQuestion(@PathVariable("quizId") Long quizId, Model model) {
        OldQuiz quiz = oldQuizUseCase.getQuiz(quizId);
        model.addAttribute("quiz", quiz);
        List<Question> availableQuestions = questionUseCase.getQuestions(); // Fetch available questions
        model.addAttribute("questions", availableQuestions);
        model.addAttribute("actionUrl", "/quiz/assignquestion/" + quizId);

        return "/quiz/assignquestion";
    }

    @PostMapping("/quiz/assignquestion/{quizId}")
    public String saveAssignedQuestions(@PathVariable("quizId") Long quizId, @ModelAttribute("quiz") OldQuiz quiz) {
        // The 'questions' property of the Quiz object should now contain the selected questions
        // Update the quiz with assigned questions
        quiz.setId(quizId);
        oldQuizUseCase.saveAssignedQuestions(quiz);
//        return "redirect:/quiz/details/" + quizId;
        return "redirect:/quiz/index";
    }

    @GetMapping("/quiz/details/{quizId}")
    public String details(@PathVariable("quizId") Long quizId, Model model) {
        OldQuiz quiz = oldQuizUseCase.getQuiz(quizId);
        model.addAttribute("quiz", quiz);
        return "/quiz/details";
    }
}
