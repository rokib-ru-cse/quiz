package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.Chapter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IChapterUseCase {
    List<Chapter> getChapters();

    Chapter getChapter(Long chapterId);

    Chapter saveChapter(Chapter chapter);

    List<Chapter> saveChapters(MultipartFile file) throws Exception;

    Chapter updateChapter(Chapter chapter);

    Chapter deleteChapter(Long id);
}
