package com.bitspondon.quiz.application.repository;

import com.bitspondon.quiz.domain.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChapterRepository extends JpaRepository<Chapter,Long> {
    Chapter findTopByOrderByIdDesc();
}
