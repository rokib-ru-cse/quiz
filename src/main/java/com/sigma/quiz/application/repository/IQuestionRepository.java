package com.sigma.quiz.application.repository;

import com.sigma.quiz.domain.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<Question, Integer> {
}
