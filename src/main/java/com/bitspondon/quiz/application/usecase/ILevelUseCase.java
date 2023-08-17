package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.Level;

import java.util.List;

public interface ILevelUseCase {
    Level getLevel(Long id);
    List<Level> getLevels();

    Level saveLevels(Level levelRequest);

    Level updateLevel(Level levelRequest);

    Level deleteLevel(Long id);
}
