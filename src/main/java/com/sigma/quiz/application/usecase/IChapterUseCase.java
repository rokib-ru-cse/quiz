package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.entities.Chapter;
import com.sigma.quiz.domain.entities.Level;

import java.util.List;

public interface IChapterUseCase {
    List<Chapter> getChapters();
    Chapter getChapter(int chapterId);

    Chapter saveChapter(Chapter chapter);

    Chapter updateChapter(Chapter chapter);

    Chapter deleteChapter(int id);
}
