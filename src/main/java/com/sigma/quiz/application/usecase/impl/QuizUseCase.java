package com.sigma.quiz.application.usecase.impl;

import com.sigma.quiz.application.repository.IChapterRepository;
import com.sigma.quiz.application.repository.ILevelRepository;
import com.sigma.quiz.application.repository.IQuizRepository;
import com.sigma.quiz.application.repository.ISubjectRepository;
import com.sigma.quiz.application.usecase.IQuizUseCase;
import com.sigma.quiz.domain.Util;
import com.sigma.quiz.domain.entities.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuizUseCase implements IQuizUseCase {
    @Autowired
    private IQuizRepository quizRepository;

    @Autowired
    private IChapterRepository chapterRepository;

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private ILevelRepository levelRepository;

    @Override
    public List<Quiz> getQuizs() {
        List<Quiz> questionList = quizRepository.findAll();
        return questionList;
//        return ReturnReponse.<quiz>builder().message("data found successfully").succeeded(true)
//                .values(questionList).build();
    }

    @Override
    public Quiz getQuiz(int quizId) {
        return quizRepository.findById(quizId).get();
    }

    @Override
    public Quiz getQuizDetails(int quizId) {
//        return quizRepository.findQuizWithAssignedQuestions(quizId);
        return null;
    }

    @Override
    public Quiz saveQuiz(Quiz quizRequest) {
//        Subject subject = subjectRepository.findById(questionRequest.getSubjectId()).get();
//        Chapter chapter = chapterRepository.findById(questionRequest.getChapterId()).get();
//        Level level = levelRepository.findById(questionRequest.getLevelId()).get();
        quizRequest.setUpdatedAt(new Date());
        quizRequest.setCreatedAt(new Date());
//        questionRequest.setLevel(level);
//        questionRequest.setSubject(subject);
//        questionRequest.setChapter(chapter);

        Quiz savedquiz = quizRepository.save(quizRequest);
        return savedquiz;
//        return ReturnReponse.<quiz>builder().message("quiz Saved Successfully").succeeded(true).value(savedquiz).build();
    }

    @Override
    public Quiz updateQuiz(Quiz quizRequest) {
        Quiz dbquiz = quizRepository.findById(quizRequest.getId()).get();
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

        Quiz updatedquiz = quizRepository.save(dbquiz);
        return updatedquiz;
//        return ReturnReponse.<quiz>builder().message("quiz Updated Successfully").succeeded(true).value(updatedquiz).build();
    }

    @Override
    public Quiz saveAssignedQuestions(Quiz quiz) {
        Quiz dbquiz = quizRepository.findById(quiz.getId()).get();

        dbquiz.setQuestions(quiz.getQuestions());

        Quiz updatedquiz = quizRepository.save(dbquiz);
        return updatedquiz;
    }

    @Override
    public Quiz deleteQuiz(int id) {
        Quiz dbquiz = quizRepository.findById(id).get();
        quizRepository.delete(dbquiz);
        return dbquiz;
//        return ReturnReponse.<quiz>builder().message("question deleted successfully").succeeded(true).value(dbquiz).build();
    }
}
