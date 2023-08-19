package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.application.repository.IChapterRepository;
import com.bitspondon.quiz.application.repository.ISubjectRepository;
import com.bitspondon.quiz.application.usecase.IChapterUseCase;
import com.bitspondon.quiz.domain.Util;
import com.bitspondon.quiz.domain.constant.ValidationMessage;
import com.bitspondon.quiz.domain.entities.Chapter;
import com.bitspondon.quiz.domain.exception.CustomException;
import com.bitspondon.quiz.domain.helper.ChapterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class ChapterUseCase implements IChapterUseCase {

    @Autowired
    private ISubjectRepository subjectRepository;
    @Autowired
    private IChapterRepository chapterRepository;

    @Override
    public List<Chapter> getChapters() {
        List<Chapter> chapterList = chapterRepository.findAll();
        return chapterList;
//        return ReturnReponse.<Chapter>builder().message("data found successfully").succeeded(true)
//                .values(chapterList).build();
    }

    @Override
    public Chapter getChapter(Long chapterId) {
        return chapterRepository.findById(chapterId).get();
    }

    @Override
    public Chapter saveChapter(Chapter chapter) {
//        Subject dbSubject = subjectRepository.findById(chapter.getSubjectId()).get();
//        chapter.setSubject(dbSubject);
        chapter.setCreatedAt(new Date());
        chapter.setUpdatedAt(new Date());
        Chapter savedChapter = chapterRepository.save(chapter);
        return savedChapter;
//        return ReturnReponse.<Chapter>builder().message("data saved successfully").succeeded(true)
//                .value(savedChapter).build();
    }

    @Override
    public List<Chapter> saveChapters(MultipartFile file) throws Exception {
        if (!Util.checkExcelFormat(file)) {
            throw new CustomException(ValidationMessage.EXCEL_FILE_FORMAT_NOT_MATCHED);
        }
        List<Chapter> chapterList = ChapterHelper.convertExcelToListOfChapters(file.getInputStream(), chapterRepository, subjectRepository);
        chapterList = chapterRepository.saveAll(chapterList);
        return chapterList;
    }


    @Override
    public Chapter updateChapter(Chapter chapter) {
//        Subject dbSubject = subjectRepository.findById(chapter.getSubjectId()).get();
        Chapter dbChapter = chapterRepository.findById(chapter.getId()).get();
        if (!Util.isNullOrWhiteSpace(chapter.getName())) {
            dbChapter.setName(chapter.getName());
        }
        dbChapter.setImage(chapter.getImage());
        dbChapter.setActive(chapter.isActive());
        dbChapter.setIcon(chapter.getIcon());
        dbChapter.setUpdatedAt(new Date());
        dbChapter.setSubject(chapter.getSubject());
//        dbChapter.setSubject(dbSubject);
        Chapter updatedChapter = chapterRepository.save(dbChapter);
        return updatedChapter;
//        return ReturnReponse.<Chapter>builder().message("data updated successfully").succeeded(true)
//                .value(updatedChapter).build();
    }

    @Override
    public Chapter deleteChapter(Long id) {
        Chapter dbChapter = chapterRepository.findById(id).get();
        chapterRepository.delete(dbChapter);
        return dbChapter;
//        return ReturnReponse.<Chapter>builder().message("chapter deleted successfully").succeeded(true).value(dbChapter).build();
    }
}
