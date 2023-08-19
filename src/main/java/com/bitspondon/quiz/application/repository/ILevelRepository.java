package com.bitspondon.quiz.application.repository;

import com.bitspondon.quiz.domain.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILevelRepository extends JpaRepository<Level,Long> {
    Level findTopByOrderByIdDesc();
}
