package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.IQuestionUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('" + Constant.ROLE_USER + "', '" + Constant.ROLE_ADMIN + "')")
@RequestMapping(ClientUrl.QUESTION)
public class QuestionClientController {


    private final IQuestionUseCase questionUseCase;

    public QuestionClientController(IQuestionUseCase questionUseCase) {
        this.questionUseCase = questionUseCase;
    }

    @GetMapping
    public ReturnReponse<Question> getQuestions() {
        List<Question> questionList = questionUseCase.getQuestions();
        return ReturnReponse.<Question>builder().message("data found successfully").succeeded(true).values(questionList).build();
    }

}
