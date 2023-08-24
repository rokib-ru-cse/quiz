package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.application.repository.*;
import com.bitspondon.quiz.application.usecase.IOldQuizUseCase;
import com.bitspondon.quiz.domain.Util;
import com.bitspondon.quiz.domain.dto.quizsubmission.QuizQuestionDTO;
import com.bitspondon.quiz.domain.dto.quizsubmission.QuizSubmissionDTO;
import com.bitspondon.quiz.domain.entities.*;
import com.bitspondon.quiz.domain.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OldQuizUseCase implements IOldQuizUseCase {
    private final IOldQuizRepository oldQuizRepository;

    private final IQuizSubmissionRepository quizSubmissionRepository;

    private final IUserRepository userRepository;

    @Override
    public List<OldQuiz> getOldQuizzes() {
        List<OldQuiz> questionList = oldQuizRepository.findAll();
        return questionList;
//        return ReturnReponse.<quiz>builder().message("data found successfully").succeeded(true)
//                .values(questionList).build();
    }

    @Override
    public OldQuiz getOldQuiz(Long quizId) {
        return oldQuizRepository.findById(quizId).get();
    }


    @Override
    public OldQuiz getOldQuizDetails(Long quizId) {
        return oldQuizRepository.findQuizWithAssignedQuestions(quizId);
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
    public OldQuiz updateOldQuiz(OldQuiz quizRequest) {
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
    public OldQuiz saveAssignedQuestionsToOldQuiz(OldQuiz quiz) {
        OldQuiz dbquiz = oldQuizRepository.findById(quiz.getId()).get();

        dbquiz.setQuestions(quiz.getQuestions());

        OldQuiz updatedquiz = oldQuizRepository.save(dbquiz);
        return updatedquiz;
    }

    @Override
    public OldQuiz deleteOldQuiz(Long id) {
        OldQuiz dbquiz = oldQuizRepository.findById(id).get();
        oldQuizRepository.delete(dbquiz);
        return dbquiz;
//        return ReturnReponse.<quiz>builder().message("question deleted successfully").succeeded(true).value(dbquiz).build();
    }

    @Override
    public QuizSubmissionDTO startOldQuiz(Long quizId) throws Exception {

        OldQuiz oldQuiz = oldQuizRepository.findQuizWithAssignedQuestions(quizId);
        //        if (liveQuiz.getQuizDate().compareTo(LocalDate.now()) > 0 && liveQuiz.getStartTime().compareTo(LocalTime.now()) > 0) {
        //            throw new CustomException("Quiz Will Be Started At " + liveQuiz.getQuizDate() + " - " + liveQuiz.getStartTime());
        //        } else if (liveQuiz.getQuizDate().isEqual(LocalDate.now()) && liveQuiz.getStartTime().compareTo(LocalTime.now()) < 0 && liveQuiz.getStartTime().plusMinutes(liveQuiz.getDuration()).compareTo(LocalTime.now()) > 0) {
        //            return liveQuiz;
        //        } else {
        //            throw new CustomException("Quiz Have Ended At " + liveQuiz.getQuizDate() + " - " + liveQuiz.getStartTime());
        //        }
        QuizSubmissionDTO quizSubmission = new QuizSubmissionDTO();
        quizSubmission.setQuizId(oldQuiz.getId());

        List<QuizQuestionDTO> quizQuestionList = new ArrayList<>();
        for (Question question : oldQuiz.getQuestions()) {
            quizQuestionList.add(Util.getQuestionWithOptions(question));
        }
        quizSubmission.setQuestions(quizQuestionList);
        return quizSubmission;

    }

    @Override
    public QuizSubmissionDTO submitOldQuiz(Long quizId, QuizSubmissionDTO quizSubmission) throws Exception {
        OldQuiz dbOldQuiz = oldQuizRepository.findById(quizId).orElse(null);
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if (dbOldQuiz == null) {
            throw new CustomException("No Live Quiz Found with id " + quizId);
        }
        if (user == null) {
            throw new CustomException("user not found");
        }

        Map<Long, Set<String>> questionAnswers = dbOldQuiz.getQuestions().stream().collect(Collectors.toMap(Question::getId, question -> new HashSet<>(question.getAnswers())));

        int marksPerQuestion = dbOldQuiz.getMarks() / questionAnswers.size();
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
        newQuizSubmission.setOldQuiz(dbOldQuiz);
        newQuizSubmission.setSubmissionTime(LocalDateTime.now());
        newQuizSubmission.setChosenAnswers(Util.chosenAnswers(quizSubmission.getQuestions()));
        newQuizSubmission.setComments("quiz was awesome");
        newQuizSubmission.setMarks(totalMarks);

        quizSubmissionRepository.save(newQuizSubmission);

        return new QuizSubmissionDTO();
    }


}
