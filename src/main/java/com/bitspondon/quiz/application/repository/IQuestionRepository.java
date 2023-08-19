package com.bitspondon.quiz.application.repository;

import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<Question, Long> {

    Question findTopByOrderByIdDesc();
}
