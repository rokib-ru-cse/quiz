package com.bitspondon.quiz.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnReponse<T> {
    private String message;
    private boolean succeeded;
    private List<T> values;
    private T value;
}
