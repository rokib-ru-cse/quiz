package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Level;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ILevelUseCase {
    Level getLevel(int id);
    List<Level> getLevels();

    Level saveLevels(Level levelRequest);

    Level updateLevel(Level levelRequest);

    Level deleteLevel(int id);
}
