package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.IQuestionUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client/question")
public class QuestionClientController {


    @Autowired
    private IQuestionUseCase questionUseCase;

    @GetMapping
    public ReturnReponse<Question> getQuestions(){
//        return questionUseCase.getQuestions();
        return null;
    }

}
