package com.bitspondon.quiz.application.repository;

import com.bitspondon.quiz.domain.entities.OldQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOldQuizRepository extends JpaRepository<OldQuiz, Long> {
    @Query("SELECT q FROM OldQuiz q LEFT JOIN FETCH q.questions WHERE q.id = :quizId")
    OldQuiz findQuizWithAssignedQuestions(@Param("quizId") long quizId);

}
