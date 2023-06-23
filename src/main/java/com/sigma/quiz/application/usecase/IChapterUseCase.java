package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Chapter;
import com.sigma.quiz.domain.entities.Level;

public interface IChapterUseCase {
    ReturnReponse<Chapter> getChapters();

    ReturnReponse<Chapter> saveChapter(Chapter chapter);

    ReturnReponse<Chapter> updateChapter(Chapter chapter);

    ReturnReponse<Chapter> deleteChapter(int id);
}
