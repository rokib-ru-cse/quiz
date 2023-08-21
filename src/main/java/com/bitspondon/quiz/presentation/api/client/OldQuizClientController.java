package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.IOldQuizUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.OldQuiz;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        return oldQuizList;
        return ReturnReponse.<OldQuiz>builder().message("data found successfully").succeeded(true).values(oldQuizList).build();
    }
}
