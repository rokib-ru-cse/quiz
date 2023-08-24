package com.bitspondon.quiz.application.repository;

import com.bitspondon.quiz.domain.entities.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
}
