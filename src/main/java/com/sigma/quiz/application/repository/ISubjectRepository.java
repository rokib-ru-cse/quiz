package com.sigma.quiz.application.repository;

import com.sigma.quiz.domain.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository extends JpaRepository<Subject,Integer> {
}
