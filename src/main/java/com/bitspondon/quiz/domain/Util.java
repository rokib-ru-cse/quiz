package com.bitspondon.quiz.domain;

import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

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

  public   static void getQuestionWithOptionsAndAnswer(Question question) {
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

}
