package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IQuestionUseCase {
    List<Question> getQuestions();

    Question getQuestion(Long questionId);

    Question saveQuestion(Question question);

//     void processQuestionExcelFile(MultipartFile file);

    List<Question> saveQuestions(MultipartFile file) throws Exception;

    Question updateQuestion(Question question);

    Question deleteQuestion(Long id);

}
