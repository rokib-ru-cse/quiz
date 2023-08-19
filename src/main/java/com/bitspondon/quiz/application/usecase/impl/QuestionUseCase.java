package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.application.repository.IChapterRepository;
import com.bitspondon.quiz.application.repository.ILevelRepository;
import com.bitspondon.quiz.application.repository.IQuestionRepository;
import com.bitspondon.quiz.application.repository.ISubjectRepository;
import com.bitspondon.quiz.application.usecase.IQuestionUseCase;
import com.bitspondon.quiz.domain.Util;
import com.bitspondon.quiz.domain.constant.ValidationMessage;
import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.bitspondon.quiz.domain.entities.Question;
import com.bitspondon.quiz.domain.exception.CustomException;
import com.bitspondon.quiz.domain.helper.QuestionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job importJob;


    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = questionRepository.findAll();
        return questionList;
//        return ReturnReponse.<Question>builder().message("data found successfully").succeeded(true)
//                .values(questionList).build();
    }


    @Override
    public Question getQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId).orElse(null);

        if (question == null) {
            // Handle the case where the question doesn't exist
            // You might want to throw an exception or return an error response
            return null;
        }
        Util.getQuestionWithOptionsAndAnswer(question);
        return question;
    }


    @Override
    public Question saveQuestion(Question questionRequest) {
        List<String> optionsList = new ArrayList<>();
        List<String> answersList = new ArrayList<>();

        for (OptionDTO option : questionRequest.getOptionList()) {
            if (!Util.isNullOrWhiteSpace(option.getName())) {
                optionsList.add(option.getName().trim());
                if (option.isAnswer()) {
                    answersList.add(option.getName().trim());
                }
            }
        }
        questionRequest.setOptions(optionsList);
        questionRequest.setAnswers(answersList);
        questionRequest.setUpdatedAt(new Date());
        questionRequest.setCreatedAt(new Date());

        Question savedQuestion = questionRepository.save(questionRequest);
        return savedQuestion;
    }

//    @Override
//    public void processQuestionExcelFile(MultipartFile file) {
//        try {
//            // Save the uploaded file to a temporary location
//            Path tempFile = Files.createTempFile("questions", ".xlsx");
//            file.transferTo(tempFile.toFile());
//
//            // Start the Spring Batch job
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addString("input.file.name", tempFile.toString())
//                    .toJobParameters();
//            jobLauncher.run(importJob, jobParameters);
//        } catch (Exception e) {
//            // Handle exceptions
//        }
//    }

    @Override
    public List<Question> saveQuestions(MultipartFile file) throws Exception {
        if (!Util.checkExcelFormat(file)) {
            throw new CustomException(ValidationMessage.EXCEL_FILE_FORMAT_NOT_MATCHED);
        }
        List<Question> questionList = QuestionHelper.convertExcelToListOfQuestions(file.getInputStream(),questionRepository,levelRepository,subjectRepository, chapterRepository);
        questionList = questionRepository.saveAll(questionList);
        return questionList;
    }


    @Override
    public Question updateQuestion(Question questionRequest) {
        Question dbQuestion = questionRepository.findById(questionRequest.getId()).orElse(null);

        if (dbQuestion == null) {
            // Handle the case where the question doesn't exist
            // You might want to throw an exception or return an error response
            return null;
        }

        List<String> optionsList = new ArrayList<>();
        List<String> answersList = new ArrayList<>();

        for (OptionDTO option : questionRequest.getOptionList()) {
            if (!Util.isNullOrWhiteSpace(option.getName())) {
                optionsList.add(option.getName().trim());
                if (option.isAnswer()) {
                    answersList.add(option.getName().trim());
                }
            }
        }
        dbQuestion.setOptions(optionsList);   // Use the correct setter method
        dbQuestion.setAnswers(answersList);   // Use the correct setter method

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

        return questionRepository.save(dbQuestion);
    }


    @Override
    public Question deleteQuestion(Long id) {
        Question dbQuestion = questionRepository.findById(id).get();
        questionRepository.delete(dbQuestion);
        return dbQuestion;
//        return ReturnReponse.<Question>builder().message("question deleted successfully").succeeded(true).value(dbQuestion).build();
    }
}
