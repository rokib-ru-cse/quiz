package com.sigma.quiz.presentation.api.client;

import com.sigma.quiz.application.usecase.IChapterUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client/chapter")
public class ChapterClientController {
    @Autowired
    private IChapterUseCase chapterUseCase;

    @GetMapping
    public ReturnReponse<Chapter> getChapters() {
        return chapterUseCase.getChapters();
    }
}
