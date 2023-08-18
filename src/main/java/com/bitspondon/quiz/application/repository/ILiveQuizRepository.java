package com.bitspondon.quiz.application.repository;

import com.bitspondon.quiz.domain.entities.LiveQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ILiveQuizRepository extends JpaRepository<LiveQuiz, Long> {
    @Query("SELECT q FROM LiveQuiz q LEFT JOIN FETCH q.questions WHERE q.id = :quizId")
    LiveQuiz findQuizWithAssignedQuestions(@Param("quizId") Long quizId);

    @Query("SELECT q FROM LiveQuiz q LEFT JOIN FETCH q.users WHERE q.id = :quizId")
    LiveQuiz findQuizWithAssignedUser(@Param("quizId") Long quizId);

}
