package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.OldQuiz;

import java.util.List;

public interface IOldQuizUseCase {
    List<OldQuiz> getQuizs();

    OldQuiz getQuiz(Long quizId);

    OldQuiz getQuizDetails(Long quizId);

    OldQuiz saveOldQuiz(OldQuiz quiz);

    OldQuiz updateQuiz(OldQuiz question);
    OldQuiz saveAssignedQuestions(OldQuiz question);

    OldQuiz deleteQuiz(Long id);

}
