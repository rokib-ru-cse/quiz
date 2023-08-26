package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.IQuestionUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.entities.Question;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ClientUrl.QUESTION)
public class QuestionClientController {


    private final IQuestionUseCase questionUseCase;

    @GetMapping
    public ReturnReponse<List<Question>> getQuestions() {
        List<Question> questionList = questionUseCase.getQuestions();
        return ReturnReponse.<List<Question>>builder().message("data found successfully").succeeded(true).value(questionList).build();
    }

}
