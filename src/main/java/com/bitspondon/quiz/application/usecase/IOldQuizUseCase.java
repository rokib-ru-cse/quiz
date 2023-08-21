package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.OldQuiz;

import java.util.List;

public interface IOldQuizUseCase {
    List<OldQuiz> getOldQuizzes();

    OldQuiz getOldQuiz(Long quizId);

    OldQuiz getOldQuizDetails(Long quizId);

    OldQuiz saveOldQuiz(OldQuiz quiz);

    OldQuiz updateOldQuiz(OldQuiz question);
    OldQuiz saveAssignedQuestionsToOldQuiz(OldQuiz question);

    OldQuiz deleteOldQuiz(Long id);

}
