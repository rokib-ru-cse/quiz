package com.sigma.quiz.presentation.api.admin;

import com.sigma.quiz.application.usecase.IQuestionUseCase;
import com.sigma.quiz.application.usecase.IQuestionUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/question")
public class QuestionAdminController {

    @Autowired
    private IQuestionUseCase  questionUseCase;

    @GetMapping
    public ReturnReponse<Question> getQuestions(){
        return questionUseCase.getQuestions();
    }

    @PostMapping
    public ReturnReponse<Question> saveQuestion(Question subjectRequest){
        return questionUseCase.saveQuestion(subjectRequest);
    }

    @PutMapping
    public ReturnReponse<Question> updateQuestion(Question subjectRequest){
        return questionUseCase.updateQuestion(subjectRequest);
    }

    @DeleteMapping
    public ReturnReponse<Question> deleteQuestion(@RequestParam int id){
        return questionUseCase.deleteQuestion(id);
    }

}
