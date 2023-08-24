package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.dto.quizsubmission.QuizSubmissionDTO;
import com.bitspondon.quiz.domain.entities.LiveQuiz;

import java.util.List;

public interface ILiveQuizUseCase {

    LiveQuiz enroll(Long quizId) throws Exception;

    LiveQuiz saveLiveQuiz(LiveQuiz quiz);

    List<LiveQuiz> getLiveQuizzes();

    LiveQuiz getLiveQuiz(Long quizId);

    QuizSubmissionDTO startLiveQuiz(Long quizId) throws Exception;
    QuizSubmissionDTO submitLiveQuiz(Long quizId,QuizSubmissionDTO quizSubmission) throws Exception;

    LiveQuiz getLiveQuizDetails(Long quizId);


    LiveQuiz updateLiveQuiz(LiveQuiz question);

    LiveQuiz saveAssignedQuestionsToLiveQuiz(LiveQuiz question);
}
