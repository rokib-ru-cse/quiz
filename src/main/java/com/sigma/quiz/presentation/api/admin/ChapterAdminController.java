package com.sigma.quiz.presentation.api.admin;

import com.sigma.quiz.application.usecase.IChapterUseCase;
import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Chapter;
import com.sigma.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/chapter")
public class ChapterAdminController {

    @Autowired
    private IChapterUseCase  chapterUseCase;

    @GetMapping
    public ReturnReponse<Chapter> getChapters() {
        return chapterUseCase.getChapters();
    }
    @PostMapping
    public ReturnReponse<Chapter> saveChapter(Chapter levelRequest) {
        return chapterUseCase.saveChapter(levelRequest);
    }
    @PutMapping
    public ReturnReponse<Chapter> updateChapter(Chapter levelRequest) {
        return chapterUseCase.updateChapter(levelRequest);
    }
    @DeleteMapping
    public ReturnReponse<Chapter> deleteChapter(@RequestParam int id) {
        return chapterUseCase.deleteChapter(id);
    }
}
