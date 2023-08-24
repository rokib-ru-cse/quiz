package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.IOldQuizUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.dto.quizsubmission.QuizSubmissionDTO;
import com.bitspondon.quiz.domain.entities.OldQuiz;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ClientUrl.OLD_QUIZ)
@PreAuthorize("hasAnyRole('" + Constant.ROLE_USER + "', '" + Constant.ROLE_ADMIN + "')")
public class OldQuizClientController {


    private final IOldQuizUseCase oldQuizUseCase;

    public OldQuizClientController(IOldQuizUseCase oldQuizUseCase) {
        this.oldQuizUseCase = oldQuizUseCase;
    }


    @GetMapping
    public ReturnReponse<OldQuiz> getOldQuizzes() {
        List<OldQuiz> oldQuizList = oldQuizUseCase.getOldQuizzes();
        return ReturnReponse.<OldQuiz>builder().message("data found successfully").succeeded(true).values(oldQuizList).build();
    }


    @GetMapping(ClientUrl.OLD_QUIZ_START + "/{" + Constant.OLD_QUIZ_ID + "}")
    public ReturnReponse<QuizSubmissionDTO> startLiveQuiz(@PathVariable(Constant.OLD_QUIZ_ID) Long quizId) throws Exception {

        QuizSubmissionDTO oldQuiz = oldQuizUseCase.startOldQuiz(quizId);

        return ReturnReponse.<QuizSubmissionDTO>builder().message("data found successfully").succeeded(true).value(oldQuiz).build();
    }

    @PostMapping(ClientUrl.OLD_QUIZ_SUBMIT + "/{" + Constant.OLD_QUIZ_ID + "}")
    public ReturnReponse<QuizSubmissionDTO> submitLiveQuiz(@PathVariable(Constant.OLD_QUIZ_ID) Long quizId, @RequestBody QuizSubmissionDTO quizSubmission) throws Exception {

        QuizSubmissionDTO oldQuiz = oldQuizUseCase.submitOldQuiz(quizId, quizSubmission);

        return ReturnReponse.<QuizSubmissionDTO>builder().message("Quiz submitted successfully").succeeded(true).value(oldQuiz).build();
    }



}
