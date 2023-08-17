package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.application.repository.*;
import com.bitspondon.quiz.domain.Util;
import com.bitspondon.quiz.domain.entities.OldQuiz;
import com.bitspondon.quiz.application.usecase.IOldQuizUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OldQuizUseCase implements IOldQuizUseCase {
    @Autowired
    private IOldQuizRepository oldQuizRepository;

    @Autowired
    private ILiveQuizRepository liveQuizRepository;

    @Autowired
    private IChapterRepository chapterRepository;

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private ILevelRepository levelRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<OldQuiz> getQuizs() {
        List<OldQuiz> questionList = oldQuizRepository.findAll();
        return questionList;
//        return ReturnReponse.<quiz>builder().message("data found successfully").succeeded(true)
//                .values(questionList).build();
    }

    @Override
    public OldQuiz getQuiz(Long quizId) {
        return oldQuizRepository.findById(quizId).get();
    }


    @Override
    public OldQuiz getQuizDetails(Long quizId) {
//        return oldQuizRepository.findQuizWithAssignedQuestions(quizId);
        return null;
    }

    @Override
    public OldQuiz saveOldQuiz(OldQuiz quizRequest) {
//        Subject subject = subjectRepository.findById(questionRequest.getSubjectId()).get();
//        Chapter chapter = chapterRepository.findById(questionRequest.getChapterId()).get();
//        Level level = levelRepository.findById(questionRequest.getLevelId()).get();
        quizRequest.setUpdatedAt(new Date());
        quizRequest.setCreatedAt(new Date());
//        questionRequest.setLevel(level);
//        questionRequest.setSubject(subject);
//        questionRequest.setChapter(chapter);

        OldQuiz savedquiz = oldQuizRepository.save(quizRequest);
        return savedquiz;
//        return ReturnReponse.<quiz>builder().message("quiz Saved Successfully").succeeded(true).value(savedquiz).build();
    }



    @Override
    public OldQuiz updateQuiz(OldQuiz quizRequest) {
        OldQuiz dbquiz = oldQuizRepository.findById(quizRequest.getId()).get();
        if (!Util.isNullOrWhiteSpace(quizRequest.getTitle())) {
            dbquiz.setTitle(quizRequest.getTitle());
        }
        if (!Util.isNullOrWhiteSpace(quizRequest.getDescription())) {
            dbquiz.setDescription(quizRequest.getDescription());
        }


        dbquiz.setUpdatedAt(new Date());
        dbquiz.setLevel(quizRequest.getLevel());
        dbquiz.setSubject(quizRequest.getSubject());
        dbquiz.setChapter(quizRequest.getChapter());

        OldQuiz updatedquiz = oldQuizRepository.save(dbquiz);
        return updatedquiz;
//        return ReturnReponse.<quiz>builder().message("quiz Updated Successfully").succeeded(true).value(updatedquiz).build();
    }

    @Override
    public OldQuiz saveAssignedQuestions(OldQuiz quiz) {
        OldQuiz dbquiz = oldQuizRepository.findById(quiz.getId()).get();

        dbquiz.setQuestions(quiz.getQuestions());

        OldQuiz updatedquiz = oldQuizRepository.save(dbquiz);
        return updatedquiz;
    }

    @Override
    public OldQuiz deleteQuiz(Long id) {
        OldQuiz dbquiz = oldQuizRepository.findById(id).get();
        oldQuizRepository.delete(dbquiz);
        return dbquiz;
//        return ReturnReponse.<quiz>builder().message("question deleted successfully").succeeded(true).value(dbquiz).build();
    }

}