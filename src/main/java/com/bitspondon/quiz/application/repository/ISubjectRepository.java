package com.bitspondon.quiz.application.repository;

import com.bitspondon.quiz.domain.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository extends JpaRepository<Subject, Long> {
    Subject findTopByOrderByIdDesc();
}
