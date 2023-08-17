package com.bitspondon.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnReponse<T> {
    private String message;
    private boolean succeeded;
    private List<T> values;
    private T value;
}
