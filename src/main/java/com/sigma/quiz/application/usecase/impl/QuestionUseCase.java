package com.sigma.quiz.application.usecase.impl;

import com.sigma.quiz.application.repository.IChapterRepository;
import com.sigma.quiz.application.repository.ILevelRepository;
import com.sigma.quiz.application.repository.IQuestionRepository;
import com.sigma.quiz.application.repository.ISubjectRepository;
import com.sigma.quiz.application.usecase.IQuestionUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.Util;
import com.sigma.quiz.domain.entities.Chapter;
import com.sigma.quiz.domain.entities.Level;
import com.sigma.quiz.domain.entities.Question;
import com.sigma.quiz.domain.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionUseCase implements IQuestionUseCase {
    @Autowired
    private IQuestionRepository questionRepository;

    @Autowired
    private IChapterRepository chapterRepository;

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private ILevelRepository levelRepository;

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = questionRepository.findAll();
        return questionList;
//        return ReturnReponse.<Question>builder().message("data found successfully").succeeded(true)
//                .values(questionList).build();
    }

    @Override
    public Question getQuestion(int questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Question saveQuestion(Question questionRequest) {
//        Subject subject = subjectRepository.findById(questionRequest.getSubjectId()).get();
//        Chapter chapter = chapterRepository.findById(questionRequest.getChapterId()).get();
//        Level level = levelRepository.findById(questionRequest.getLevelId()).get();
        questionRequest.setUpdatedAt(new Date());
        questionRequest.setCreatedAt(new Date());
//        questionRequest.setLevel(level);
//        questionRequest.setSubject(subject);
//        questionRequest.setChapter(chapter);

        Question savedQuestion = questionRepository.save(questionRequest);
        return savedQuestion;
//        return ReturnReponse.<Question>builder().message("Question Saved Successfully").succeeded(true).value(savedQuestion).build();
    }

    @Override
    public Question updateQuestion(Question questionRequest) {
        Question dbQuestion = questionRepository.findById(questionRequest.getId()).get();

//        Subject subject = subjectRepository.findById(questionRequest.getSubjectId()).get();
//        Chapter chapter = chapterRepository.findById(questionRequest.getChapterId()).get();
//        Level level = levelRepository.findById(questionRequest.getLevelId()).get();

        if (!Util.isNullOrWhiteSpace(questionRequest.getTitle())) {
            dbQuestion.setTitle(questionRequest.getTitle());
        }
        if (!Util.isNullOrWhiteSpace(questionRequest.getDescription())) {
            dbQuestion.setDescription(questionRequest.getDescription());
        }


        dbQuestion.setUpdatedAt(new Date());
        dbQuestion.setLevel(questionRequest.getLevel());
        dbQuestion.setSubject(questionRequest.getSubject());
        dbQuestion.setChapter(questionRequest.getChapter());

        Question updatedQuestion = questionRepository.save(dbQuestion);
        return updatedQuestion;
//        return ReturnReponse.<Question>builder().message("Question Updated Successfully").succeeded(true).value(updatedQuestion).build();
    }

    @Override
    public Question deleteQuestion(int id) {
        Question dbQuestion = questionRepository.findById(id).get();
        questionRepository.delete(dbQuestion);
        return dbQuestion;
//        return ReturnReponse.<Question>builder().message("question deleted successfully").succeeded(true).value(dbQuestion).build();
    }
}
