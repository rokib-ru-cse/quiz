package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.*;
import com.bitspondon.quiz.domain.entities.LiveQuiz;
import com.bitspondon.quiz.domain.entities.Question;
import com.bitspondon.quiz.domain.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/api/v1/admin/question")
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


    @GetMapping("/livequiz/index")
    public String getQuizs(Model model) {
//        return oldQuizUseCase.getQuizs();
        model.addAttribute("quizzes", liveQuizUseCase.getLiveQuizs());
        return "/livequiz/index";
    }

    @GetMapping("/livequiz/details/{quizId}")
    public String details(@PathVariable("quizId") Long quizId, Model model) throws CustomException {
        LiveQuiz quiz = liveQuizUseCase.getLiveQuiz(quizId);
        model.addAttribute("quiz", quiz);
        return "/livequiz/details";
    }

    @GetMapping("/livequiz/create")
    public String create(Model model) {
//        return oldQuizUseCase.saveQuiz(subjectRequest);

        model.addAttribute("quiz", new LiveQuiz());
        model.addAttribute("levelOptions", levelUseCase.getLevels());
        model.addAttribute("subjectOptions", subjectUseCase.getSubjects());
        model.addAttribute("chapterOptions", chapterUseCase.getChapters());
        model.addAttribute("actionUrl", "/livequiz/create");

        return "/livequiz/create";
    }

    @PostMapping("/livequiz/create")
    public String saveQuiz(@ModelAttribute("quiz") LiveQuiz quiz) {
//        System.out.println(quiz.toString());

        liveQuizUseCase.saveLiveQuiz(quiz);
        return "redirect:/livequiz/index";
    }

    @GetMapping("/livequiz/enroll/{quizId}")
    public String enroll(@PathVariable("quizId") Long quizId) throws Exception {

        LiveQuiz quiz = liveQuizUseCase.enroll(quizId);
        return "redirect:/livequiz/details/" + quizId;
    }


    @GetMapping("/livequiz/edit/{quizId}")
    public String edit(@PathVariable("quizId") Long quizId, Model model) throws Exception {
//        return oldQuizUseCase.saveQuiz(subjectRequest);
        LiveQuiz quiz = liveQuizUseCase.getLiveQuiz(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("levelOptions", levelUseCase.getLevels());
        model.addAttribute("subjectOptions", subjectUseCase.getSubjects());
        model.addAttribute("chapterOptions", chapterUseCase.getChapters());
        model.addAttribute("actionUrl", "/livequiz/edit/" + quizId);

        return "/livequiz/create";
    }


    @PostMapping("/livequiz/edit/{quizId}")
    public String editQuiz(@PathVariable("quizId") Long quizId, @ModelAttribute("quiz") LiveQuiz quiz) {
//        return oldQuizUseCase.saveQuiz(subjectRequest);
        quiz.setId(quizId);
        liveQuizUseCase.updateLiveQuiz(quiz);
        return "redirect:/livequiz/index";
    }

    @GetMapping("/livequiz/assignquestion/{quizId}")
    public String assignQuestion(@PathVariable("quizId") Long quizId, Model model) {
        LiveQuiz quiz = liveQuizUseCase.getLiveQuiz(quizId);
        model.addAttribute("quiz", quiz);
        List<Question> availableQuestions = questionUseCase.getQuestions(); // Fetch available questions
        model.addAttribute("questions", availableQuestions);
        model.addAttribute("actionUrl", "/livequiz/assignquestion/" + quizId);

        return "/livequiz/assignquestion";
    }

    @PostMapping("/livequiz/assignquestion/{quizId}")
    public String saveAssignedQuestions(@PathVariable("quizId") Long quizId, @ModelAttribute("quiz") LiveQuiz quiz) {
        // The 'questions' property of the Quiz object should now contain the selected questions
        // Update the quiz with assigned questions
        quiz.setId(quizId);
        liveQuizUseCase.saveAssignedQuestionsToLiveQuiz(quiz);
//        return "redirect:/quiz/details/" + quizId;
        return "redirect:/livequiz/index";
    }

    @GetMapping("/livequiz/startlivequiz/{quizId}")
    public String startLiveQuiz(@PathVariable("quizId") Long quizId, Model model) throws Exception {

        LiveQuiz quiz = liveQuizUseCase.startLiveQuiz(quizId);
//        System.out.println("quiz with id *************************************************** " + quizId + " " + quiz.toString());
//        logger.info(quiz.toString());
        model.addAttribute("quiz", quiz);
        List<Question> availableQuestions = questionUseCase.getQuestions(); // Fetch available questions
        model.addAttribute("questions", availableQuestions);
        model.addAttribute("actionUrl", "/livequiz/startlivequiz/" + quizId);
        return "/livequiz/startlivequiz";
    }

    @PostMapping("/livequiz/startlivequiz/{quizId}")
    public String processLiveQuizSubmission(@PathVariable("quizId") int quizId, @ModelAttribute("quiz") LiveQuiz quiz) throws Exception {
//        LiveQuiz quiz = liveQuizUseCase.getLiveQuiz(quizId);
//        model.addAttribute("quiz", quiz);
//        List<Question> availableQuestions = questionUseCase.getQuestions(); // Fetch available questions
//        model.addAttribute("questions", availableQuestions);
//        model.addAttribute("actionUrl", "/livequiz/assignquestion/" + quizId);
//        LiveQuiz quiz = liveQuizUseCase.enroll(quizId);

        return "redirect:/livequiz/index";
    }


    /**
     * <!-- Convert a string to a date object -->
     * <p th:text="${#dates.parse('2023-07-01', 'yyyy-MM-dd')}">Date</p>
     * <p>
     * <!-- Format the date object using the 'dd-MM-yyyy' pattern -->
     * <p th:text="${#dates.format(#dates.parse('2023-07-01', 'yyyy-MM-dd'), 'dd-MM-yyyy')}">Formatted Date</p>
     */


}
