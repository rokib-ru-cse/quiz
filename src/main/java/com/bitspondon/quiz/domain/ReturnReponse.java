package com.bitspondon.quiz.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnReponse<T> {
    private String message;
    private boolean succeeded;
    private T value;
}
