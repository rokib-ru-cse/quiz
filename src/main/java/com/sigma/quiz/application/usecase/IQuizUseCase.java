package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.entities.Quiz;

import java.util.List;

public interface IQuizUseCase {
    List<Quiz> getQuizs();

    Quiz getQuiz(int questionId);
    Quiz getQuizDetails(int quizId);

    Quiz saveQuiz(Quiz quiz);

    Quiz updateQuiz(Quiz question);
    Quiz saveAssignedQuestions(Quiz question);

    Quiz deleteQuiz(int id);

}
