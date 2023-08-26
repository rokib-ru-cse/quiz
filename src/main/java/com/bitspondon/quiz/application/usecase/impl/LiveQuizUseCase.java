package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.application.repository.ILiveQuizRepository;
import com.bitspondon.quiz.application.repository.IQuizSubmissionRepository;
import com.bitspondon.quiz.application.repository.IUserRepository;
import com.bitspondon.quiz.application.usecase.ILiveQuizUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.Util;
import com.bitspondon.quiz.domain.constant.ValidationMessage;
import com.bitspondon.quiz.domain.dto.quizsubmission.QuizQuestionDTO;
import com.bitspondon.quiz.domain.dto.quizsubmission.QuizSubmissionDTO;
import com.bitspondon.quiz.domain.entities.LiveQuiz;
import com.bitspondon.quiz.domain.entities.Question;
import com.bitspondon.quiz.domain.entities.QuizSubmission;
import com.bitspondon.quiz.domain.entities.User;
import com.bitspondon.quiz.domain.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LiveQuizUseCase implements ILiveQuizUseCase {


    private final ILiveQuizRepository liveQuizRepository;
    private final IUserRepository userRepository;
    private final IQuizSubmissionRepository quizSubmissionRepository;

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
    public List<LiveQuiz> getLiveQuizzes() {
        List<LiveQuiz> liveQuizList = liveQuizRepository.findAll();
        return liveQuizList;
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
    public QuizSubmissionDTO startLiveQuiz(Long quizId) throws Exception {

        LiveQuiz liveQuiz = liveQuizRepository.findQuizWithAssignedQuestions(quizId);
        //        if (liveQuiz.getQuizDate().compareTo(LocalDate.now()) > 0 && liveQuiz.getStartTime().compareTo(LocalTime.now()) > 0) {
        //            throw new CustomException("Quiz Will Be Started At " + liveQuiz.getQuizDate() + " - " + liveQuiz.getStartTime());
        //        } else if (liveQuiz.getQuizDate().isEqual(LocalDate.now()) && liveQuiz.getStartTime().compareTo(LocalTime.now()) < 0 && liveQuiz.getStartTime().plusMinutes(liveQuiz.getDuration()).compareTo(LocalTime.now()) > 0) {
        //            return liveQuiz;
        //        } else {
        //            throw new CustomException("Quiz Have Ended At " + liveQuiz.getQuizDate() + " - " + liveQuiz.getStartTime());
        //        }
        QuizSubmissionDTO quizSubmission = new QuizSubmissionDTO();
        quizSubmission.setQuizId(liveQuiz.getId());

        List<QuizQuestionDTO> quizQuestionList = new ArrayList<>();
        for (Question question : liveQuiz.getQuestions()) {
            quizQuestionList.add(Util.getQuestionWithOptions(question));
        }
        quizSubmission.setQuestions(quizQuestionList);
        return quizSubmission;

    }

    @Override
    public QuizSubmissionDTO submitLiveQuiz(Long quizId, QuizSubmissionDTO quizSubmission) throws Exception {
        LiveQuiz dbLiveQuiz = liveQuizRepository.findById(quizId).orElse(null);
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if (dbLiveQuiz == null) {
            throw new CustomException("No Live Quiz Found with id " + quizId);
        }
        if (user == null) {
            throw new CustomException("user not found");
        }

        Map<Long, Set<String>> questionAnswers = dbLiveQuiz.getQuestions().stream().collect(Collectors.toMap(Question::getId, question -> new HashSet<>(question.getAnswers())));

        int marksPerQuestion = dbLiveQuiz.getMarks() / questionAnswers.size();
        int totalCorrectAnswers = 0;

        for (QuizQuestionDTO questionDTO : quizSubmission.getQuestions()) {
            Set<String> correctAnswers = questionAnswers.get(questionDTO.getQuestionId());
            if (correctAnswers != null && correctAnswers.containsAll(questionDTO.getChoices())) {
                totalCorrectAnswers++;
            }
        }

        int totalMarks = totalCorrectAnswers * marksPerQuestion;

        QuizSubmission newQuizSubmission = new QuizSubmission();
        newQuizSubmission.setUser(user);
        newQuizSubmission.setLiveQuiz(dbLiveQuiz);
        newQuizSubmission.setSubmissionTime(LocalDateTime.now());
        newQuizSubmission.setChosenAnswers(Util.chosenAnswers(quizSubmission.getQuestions()));
        newQuizSubmission.setComments("quiz was awesome");
        newQuizSubmission.setMarks(totalMarks);

        quizSubmissionRepository.save(newQuizSubmission);

        return new QuizSubmissionDTO();
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
