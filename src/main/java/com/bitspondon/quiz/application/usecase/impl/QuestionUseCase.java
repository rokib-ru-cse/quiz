package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.domain.Util;
import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.bitspondon.quiz.application.repository.IChapterRepository;
import com.bitspondon.quiz.application.repository.ILevelRepository;
import com.bitspondon.quiz.application.repository.IQuestionRepository;
import com.bitspondon.quiz.application.repository.ISubjectRepository;
import com.bitspondon.quiz.application.usecase.IQuestionUseCase;
import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = questionRepository.findAll();
        return questionList;
//        return ReturnReponse.<Question>builder().message("data found successfully").succeeded(true)
//                .values(questionList).build();
    }

    @Override
    public Question getQuestion(Long questionId) {

        Question question = questionRepository.findById(questionId).get();
        String[] options = question.getOptions().split("\\|");
//        String[] answers = question.getAnswers().split("|");
        List<OptionDTO> optionList = new ArrayList<>();
        for (String option : options) {
            OptionDTO opt = new OptionDTO();
            opt.setName(option);
            if (question.getAnswers().contains(option)) {
                opt.setAnswer(true);
            }
            optionList.add(opt);
        }
        question.setOptionList(optionList);
        return question;
    }

    @Override
    public Question saveQuestion(Question questionRequest) {

        StringBuilder options = new StringBuilder();
        StringBuilder answers = new StringBuilder();

        for (OptionDTO option : questionRequest.getOptionList()) {
            if (!Util.isNullOrWhiteSpace(option.getName())) {
                options.append(option.getName()).append("|");
                if (option.isAnswer()) {
                    answers.append(option.getName()).append("|");
                }
            }
        }
        if (options.length() > 1) {
            questionRequest.setOptions(options.substring(0, options.length() - 1));
        }
        if (answers.length() > 1) {
            questionRequest.setAnswers(answers.substring(0, answers.length() - 1));
        }
        questionRequest.setUpdatedAt(new Date());
        questionRequest.setCreatedAt(new Date());

        Question savedQuestion = questionRepository.save(questionRequest);
        return savedQuestion;
//        return ReturnReponse.<Question>builder().message("Question Saved Successfully").succeeded(true).value(savedQuestion).build();
    }

    @Override
    public Question updateQuestion(Question questionRequest) {
        Question dbQuestion = questionRepository.findById(questionRequest.getId()).get();


        StringBuilder options = new StringBuilder();
        StringBuilder answers = new StringBuilder();

        for (OptionDTO option : questionRequest.getOptionList()) {
            if (!Util.isNullOrWhiteSpace(option.getName())) {
                options.append(option.getName()).append("|");
                if (option.isAnswer()) {
                    answers.append(option.getName()).append("|");
                }
            }
        }
        if (options.length() > 1) {
            dbQuestion.setOptions(options.substring(0, options.length() - 1));
        }
        if (answers.length() > 1) {
            dbQuestion.setAnswers(answers.substring(0, answers.length() - 1));
        }


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
//        return ReturnReponse.<Question>builder().message("Question Updated Successfully").succeeded(true).value(updatedQuestion).build();
    }

    @Override
    public Question deleteQuestion(Long id) {
        Question dbQuestion = questionRepository.findById(id).get();
        questionRepository.delete(dbQuestion);
        return dbQuestion;
//        return ReturnReponse.<Question>builder().message("question deleted successfully").succeeded(true).value(dbQuestion).build();
    }
}
