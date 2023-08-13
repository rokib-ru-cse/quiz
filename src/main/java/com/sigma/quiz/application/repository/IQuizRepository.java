package com.sigma.quiz.application.repository;

import com.sigma.quiz.domain.entities.Question;
import com.sigma.quiz.domain.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IQuizRepository extends JpaRepository<Quiz, Integer> {
//    @Query("SELECT q FROM Quiz q LEFT JOIN FETCH q.questions WHERE q.id = :quizId")
//    Quiz findQuizWithAssignedQuestions(@Param("quizId") int quizId);

}
