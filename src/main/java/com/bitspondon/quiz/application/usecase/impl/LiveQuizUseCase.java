package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.application.repository.*;
import com.bitspondon.quiz.application.usecase.ILiveQuizUseCase;
import com.bitspondon.quiz.domain.Util;
import com.bitspondon.quiz.domain.constant.ValidationMessage;
import com.bitspondon.quiz.domain.entities.LiveQuiz;
import com.bitspondon.quiz.domain.entities.Question;
import com.bitspondon.quiz.domain.entities.User;
import com.bitspondon.quiz.domain.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LiveQuizUseCase implements ILiveQuizUseCase {


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


    //---------------------
    @Override
    public LiveQuiz enroll(Long quizId) throws Exception {
//        LiveQuiz dbQuiz = liveQuizRepository.findQuizWithAssignedUser(quizId);
        LiveQuiz dbQuiz = liveQuizRepository.findById(quizId).get();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).get();

        if (dbQuiz.getUsers().size() + 1 > dbQuiz.getTotalParticipant()) {
            throw new CustomException(ValidationMessage.LIVE_QUIZ_ENROLLMENT_CLOSED);
        } else if (dbQuiz.getUsers().stream().anyMatch(u -> u.getId().equals(user.getId()))) {
            throw new CustomException(ValidationMessage.USER_ALREADY_ASSIGNED_TO_LIVE_QUIZ + dbQuiz.getTitle());
        }

        dbQuiz.getUsers().add(user);
        liveQuizRepository.save(dbQuiz);
        return dbQuiz;
    }

    @Override
    public LiveQuiz saveLiveQuiz(LiveQuiz quizRequest) {
//        Subject subject = subjectRepository.findById(questionRequest.getSubjectId()).get();
//        Chapter chapter = chapterRepository.findById(questionRequest.getChapterId()).get();
//        Level level = levelRepository.findById(questionRequest.getLevelId()).get();
        quizRequest.setUpdatedAt(new Date());
        quizRequest.setCreatedAt(new Date());
//        questionRequest.setLevel(level);
//        questionRequest.setSubject(subject);
//        questionRequest.setChapter(chapter);

        LiveQuiz savedquiz = liveQuizRepository.save(quizRequest);
        return savedquiz;
//        return ReturnReponse.<quiz>builder().message("quiz Saved Successfully").succeeded(true).value(savedquiz).build();
    }

    @Override
    public List<LiveQuiz> getLiveQuizs() {
        List<LiveQuiz> questionList = liveQuizRepository.findAll();
        return questionList;
//        return ReturnReponse.<quiz>builder().message("data found successfully").succeeded(true)
//                .values(questionList).build();
    }

    @Override
    public LiveQuiz getLiveQuiz(Long quizId) {
        Optional<LiveQuiz> liveQuiz = liveQuizRepository.findById(quizId);
        if (liveQuiz.isPresent()) {
            return liveQuiz.get();
        } else {
//            throw new CustomException("No Quiz With Id " + quizId,Util.isAdminRequest());
            return null;
        }
    }

    @Override
    public LiveQuiz startLiveQuiz(Long quizId) throws Exception {

        LiveQuiz liveQuiz = liveQuizRepository.findQuizWithAssignedQuestions(quizId);
        //        if (liveQuiz.getQuizDate().compareTo(LocalDate.now()) > 0 && liveQuiz.getStartTime().compareTo(LocalTime.now()) > 0) {
        //            throw new CustomException("Quiz Will Be Started At " + liveQuiz.getQuizDate() + " - " + liveQuiz.getStartTime());
        //        } else if (liveQuiz.getQuizDate().isEqual(LocalDate.now()) && liveQuiz.getStartTime().compareTo(LocalTime.now()) < 0 && liveQuiz.getStartTime().plusMinutes(liveQuiz.getDuration()).compareTo(LocalTime.now()) > 0) {
        //            return liveQuiz;
        //        } else {
        //            throw new CustomException("Quiz Have Ended At " + liveQuiz.getQuizDate() + " - " + liveQuiz.getStartTime());
        //        }

        for (Question question : liveQuiz.getQuestions()) {
            Util.getQuestionWithOptionsAndAnswer(question);
        }
        return liveQuiz;

    }


    @Override
    public LiveQuiz getLiveQuizDetails(Long quizId) {
//        return oldQuizRepository.findQuizWithAssignedQuestions(quizId);
        return null;
    }


    @Override
    public LiveQuiz updateLiveQuiz(LiveQuiz quizRequest) {
        LiveQuiz dbquiz = liveQuizRepository.findById(quizRequest.getId()).get();
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

        LiveQuiz updatedquiz = liveQuizRepository.save(dbquiz);
        return updatedquiz;
//        return ReturnReponse.<quiz>builder().message("quiz Updated Successfully").succeeded(true).value(updatedquiz).build();
    }

    @Override
    public LiveQuiz saveAssignedQuestionsToLiveQuiz(LiveQuiz quiz) {
        LiveQuiz dbquiz = liveQuizRepository.findById(quiz.getId()).get();

        dbquiz.setQuestions(quiz.getQuestions());

        LiveQuiz updatedquiz = liveQuizRepository.save(dbquiz);
        return updatedquiz;
    }


}
