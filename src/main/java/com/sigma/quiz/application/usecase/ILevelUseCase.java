package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Level;
import org.springframework.stereotype.Component;

public interface ILevelUseCase {
    ReturnReponse<Level> getLevels();

    ReturnReponse<Level> saveLevels(Level levelRequest);

    ReturnReponse<Level> updateLevel(Level levelRequest);

    ReturnReponse<Level> deleteLevel(int id);
}
