package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.dto.level.LevelRequest;
import com.sigma.quiz.domain.dto.level.LevelResponse;
import org.springframework.stereotype.Component;

@Component
public interface ILevelUseCase {
    ReturnReponse<LevelResponse> getLevels();

    ReturnReponse<LevelResponse> saveLevels(LevelRequest levelRequest);

    ReturnReponse<LevelResponse> updateLevel(LevelRequest levelRequest);

    ReturnReponse<LevelResponse> deleteLevel(int id);
}
