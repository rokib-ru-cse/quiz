package com.sigma.quiz.application.repository;

import com.sigma.quiz.domain.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChapterRepository extends JpaRepository<Chapter,Integer> {
}
