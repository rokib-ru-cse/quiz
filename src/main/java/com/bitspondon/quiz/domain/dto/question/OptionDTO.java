package com.bitspondon.quiz.domain.dto.question;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionDTO {
    private String name;
    private boolean isAnswer;
}
