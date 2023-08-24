package com.bitspondon.quiz.domain;

import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.bitspondon.quiz.domain.dto.quizsubmission.QuizQuestionDTO;
import com.bitspondon.quiz.domain.entities.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public class Util {

    public static boolean isNullOrWhiteSpace(String data) {
        if (data == null) {
            return true;
        } else if ("".equals(data.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAdminRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(Constant.ROLE_ADMIN));
    }

    public static void getQuestionWithOptionsAndAnswer(Question question) {
        List<String> optionsList = question.getOptions();
        List<OptionDTO> optionDTOList = new ArrayList<>();
        for (String option : optionsList) {
            OptionDTO opt = new OptionDTO();
            opt.setName(option);
            if (question.getAnswers().contains(option)) {
                opt.setAnswer(true);
            }
            optionDTOList.add(opt);
        }
        question.setOptionList(optionDTOList);
    }

    public static QuizQuestionDTO getQuestionWithOptions(Question question) {
        QuizQuestionDTO quizQuestion = new QuizQuestionDTO();
        quizQuestion.setQuestionId(question.getId());
        quizQuestion.setChoices(question.getOptions());
//        List<String> choices = new ArrayList<>();

//        List<String> optionsList = question.getOptions();
////        List<OptionDTO> optionDTOList = new ArrayList<>();
//        for (String option : optionsList) {
////            OptionDTO opt = new OptionDTO();
////            opt.setName(option);
////            if (question.getAnswers().contains(option)) {
////                opt.setAnswer(true);
////            }
////            optionDTOList.add(opt);
//            choices.add();
//        }
//        question.setOptionList(optionDTOList);
        return quizQuestion;
    }

    public static String chosenAnswers(List<QuizQuestionDTO> questionDTOList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Long, List<String>> chosenAnswersMap = new HashMap<>(questionDTOList.size());

        for (QuizQuestionDTO questionDTO : questionDTOList) {
            chosenAnswersMap.put(questionDTO.getQuestionId(), List.copyOf(questionDTO.getChoices()));
        }
        return objectMapper.writeValueAsString(chosenAnswersMap);
    }

    public static boolean checkExcelFormat(MultipartFile file) {
        String type = file.getContentType();
        return type.equals(Constant.XLS_FORMAT) || type.equals(Constant.XLSX_FORMAT);
    }


}
