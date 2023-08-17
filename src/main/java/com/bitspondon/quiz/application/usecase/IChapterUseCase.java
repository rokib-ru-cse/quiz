package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.Chapter;

import java.util.List;

public interface IChapterUseCase {
    List<Chapter> getChapters();
    Chapter getChapter(Long chapterId);

    Chapter saveChapter(Chapter chapter);

    Chapter updateChapter(Chapter chapter);

    Chapter deleteChapter(Long id);
}
