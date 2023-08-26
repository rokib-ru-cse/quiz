package com.bitspondon.quiz.application.repository;

import com.bitspondon.quiz.domain.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IChapterRepository extends JpaRepository<Chapter,Long> {
    Chapter findTopByOrderByIdDesc();
    List<Chapter> findBySubject_Id(long subjectId);

}
