package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.IChapterUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.Chapter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChapterClientController {
    private final IChapterUseCase chapterUseCase;

    @GetMapping(ClientUrl.CHAPTER)
    public ReturnReponse<List<Chapter>> getChapters() {
        List<Chapter> chapterList = chapterUseCase.getChapters();
        return ReturnReponse.<List<Chapter>>builder().message("data found successfully").succeeded(true).value(chapterList).build();
    }

    @GetMapping(ClientUrl.CHAPTER_BY_SUBJECT_ID + "/{" + Constant.SUBJECT_ID + "}")
    public ReturnReponse<List<Chapter>> fetchChaptersBySubject(@PathVariable(Constant.SUBJECT_ID) long subjectId) {
        List<Chapter> chaptersBySubject = chapterUseCase.getChaptersBySubject(subjectId);
        return ReturnReponse.<List<Chapter>>builder().message("data found successfully").succeeded(true).value(chaptersBySubject).build();
    }
}
