package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.ILiveQuizUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.LiveQuiz;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('" + Constant.ROLE_USER + "', '" + Constant.ROLE_ADMIN + "')")
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


}
