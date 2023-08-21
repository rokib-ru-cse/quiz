package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.IChapterUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.entities.Chapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ClientUrl.CHAPTER)
public class ChapterClientController {
    private final IChapterUseCase chapterUseCase;

    public ChapterClientController(IChapterUseCase chapterUseCase) {
        this.chapterUseCase = chapterUseCase;
    }

    @GetMapping
    public ReturnReponse<Chapter> getChapters() {
        List<Chapter> chapterList = chapterUseCase.getChapters();
        return ReturnReponse.<Chapter>builder().message("data found successfully").succeeded(true).values(chapterList).build();
    }


}
