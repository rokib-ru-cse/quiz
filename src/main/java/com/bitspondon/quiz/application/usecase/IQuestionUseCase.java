package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.Question;

import java.util.List;

public interface IQuestionUseCase {
    List<Question> getQuestions();

    Question getQuestion(Long questionId);

    Question saveQuestion(Question question);

    Question updateQuestion(Question question);

    Question deleteQuestion(Long id);

}
