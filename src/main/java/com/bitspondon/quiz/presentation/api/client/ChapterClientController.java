package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.IChapterUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.entities.Chapter;
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
//        return chapterUseCase.getChapters();
        return null;
    }
}
