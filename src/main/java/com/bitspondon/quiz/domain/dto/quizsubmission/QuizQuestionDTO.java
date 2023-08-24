package com.bitspondon.quiz.domain.dto.quizsubmission;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizQuestionDTO {
    private long questionId;
    private List<String> choices;
}
