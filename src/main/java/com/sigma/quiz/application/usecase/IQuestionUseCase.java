package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.entities.Question;

import java.util.List;

public interface IQuestionUseCase {
    List<Question> getQuestions();

    Question getQuestion(int questionId);

    Question saveQuestion(Question question);

    Question updateQuestion(Question question);

    Question deleteQuestion(int id);

}
