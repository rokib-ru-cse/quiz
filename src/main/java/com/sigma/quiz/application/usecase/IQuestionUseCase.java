package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Question;

public interface IQuestionUseCase {
    ReturnReponse<Question> getQuestions();

    ReturnReponse<Question> saveQuestion(Question question);

    ReturnReponse<Question> updateQuestion(Question question);

    ReturnReponse<Question> deleteQuestion(int id);

}
