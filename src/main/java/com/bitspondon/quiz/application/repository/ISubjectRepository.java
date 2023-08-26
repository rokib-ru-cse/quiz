package com.bitspondon.quiz.application.repository;

import com.bitspondon.quiz.domain.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubjectRepository extends JpaRepository<Subject, Long> {
    Subject findTopByOrderByIdDesc();
    List<Subject> findByLevel_Id(long levelId); // Adjust the method name to use 'findByLevel_Id'
}
