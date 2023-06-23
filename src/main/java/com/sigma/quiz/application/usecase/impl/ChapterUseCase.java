package com.sigma.quiz.application.usecase.impl;

import com.sigma.quiz.application.repository.IChapterRepository;
import com.sigma.quiz.application.repository.ISubjectRepository;
import com.sigma.quiz.application.usecase.IChapterUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.Util;
import com.sigma.quiz.domain.entities.Chapter;
import com.sigma.quiz.domain.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChapterUseCase implements IChapterUseCase {

    @Autowired
    private ISubjectRepository subjectRepository;
    @Autowired
    private IChapterRepository chapterRepository;

    @Override
    public ReturnReponse<Chapter> getChapters() {
        List<Chapter> chapterList = chapterRepository.findAll();
        return ReturnReponse.<Chapter>builder().message("data found successfully").succeeded(true)
                .values(chapterList).build();
    }

    @Override
    public ReturnReponse<Chapter> saveChapter(Chapter chapter) {
        Subject dbSubject = subjectRepository.findById(chapter.getSubjectId()).get();
        chapter.setSubject(dbSubject);
        chapter.setCreatedAt(new Date());
        chapter.setUpdatedAt(new Date());
        Chapter savedChapter = chapterRepository.save(chapter);
        return ReturnReponse.<Chapter>builder().message("data saved successfully").succeeded(true)
                .value(savedChapter).build();
    }

    @Override
    public ReturnReponse<Chapter> updateChapter(Chapter chapter) {
        Subject dbSubject = subjectRepository.findById(chapter.getSubjectId()).get();
        Chapter dbChapter = chapterRepository.findById(chapter.getId()).get();
        if (!Util.isNullOrWhiteSpace(chapter.getName())) {
            dbChapter.setName(chapter.getName());
        }
        dbChapter.setImage(chapter.getImage());
        dbChapter.setActive(chapter.isActive());
        dbChapter.setIcon(chapter.getIcon());
        dbChapter.setUpdatedAt(new Date());
        dbChapter.setSubject(dbSubject);
        Chapter updatedChapter = chapterRepository.save(dbChapter);

        return ReturnReponse.<Chapter>builder().message("data updated successfully").succeeded(true)
                .value(updatedChapter).build();
    }

    @Override
    public ReturnReponse<Chapter> deleteChapter(int id) {
        Chapter dbChapter = chapterRepository.findById(id).get();
        chapterRepository.delete(dbChapter);
        return ReturnReponse.<Chapter>builder().message("chapter deleted successfully").succeeded(true).value(dbChapter).build();
    }
}
