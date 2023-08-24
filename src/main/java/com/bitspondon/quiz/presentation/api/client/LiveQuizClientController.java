package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.ILiveQuizUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.dto.quizsubmission.QuizSubmissionDTO;
import com.bitspondon.quiz.domain.entities.LiveQuiz;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('" + Constant.ROLE_USER + "', '" + Constant.ROLE_ADMIN + "')")
//@PreAuthorize("hasAnyRole('" + Constant.ROLE_USER + "')")
@RequestMapping(ClientUrl.LIVE_QUIZ)
public class LiveQuizClientController {

    private final ILiveQuizUseCase liveQuizUseCase;

    public LiveQuizClientController(ILiveQuizUseCase liveQuizUseCase) {
        this.liveQuizUseCase = liveQuizUseCase;
    }


    @GetMapping
    public ReturnReponse<LiveQuiz> getLiveQuizzes() {
        List<LiveQuiz> liveQuizList = liveQuizUseCase.getLiveQuizzes();
        return ReturnReponse.<LiveQuiz>builder().message("data found successfully").succeeded(true).values(liveQuizList).build();
    }

    @GetMapping(ClientUrl.LIVE_QUIZ_ENROLL + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public ReturnReponse<String> enroll(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId) throws Exception {
        LiveQuiz quiz = liveQuizUseCase.enroll(quizId);
        return ReturnReponse.<String>builder().message("quiz enrollment successfully done").succeeded(true).value(null).build();
    }


    @GetMapping(ClientUrl.LIVE_QUIZ_START + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public ReturnReponse<QuizSubmissionDTO> startLiveQuiz(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId) throws Exception {

        QuizSubmissionDTO liveQuiz = liveQuizUseCase.startLiveQuiz(quizId);

        return ReturnReponse.<QuizSubmissionDTO>builder().message("data found successfully").succeeded(true).value(liveQuiz).build();
    }

    @PostMapping(ClientUrl.LIVE_QUIZ_SUBMIT + "/{" + Constant.LIVE_QUIZ_ID + "}")
    public ReturnReponse<QuizSubmissionDTO> submitLiveQuiz(@PathVariable(Constant.LIVE_QUIZ_ID) Long quizId, @RequestBody QuizSubmissionDTO quizSubmission) throws Exception {

        QuizSubmissionDTO liveQuiz = liveQuizUseCase.submitLiveQuiz(quizId, quizSubmission);

        return ReturnReponse.<QuizSubmissionDTO>builder().message("Quiz submitted successfully").succeeded(true).value(liveQuiz).build();
    }


}
