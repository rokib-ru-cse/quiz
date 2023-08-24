package com.bitspondon.quiz.domain.dto.quizsubmission;

import lombok.*;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizSubmissionDTO {
    private long quizId;
    private List<QuizQuestionDTO> questions;
}
