package com.sigma.quiz.application.repository;

import com.sigma.quiz.domain.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILevelRepository extends JpaRepository<Level,Integer> {
}
