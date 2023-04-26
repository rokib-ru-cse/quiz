package com.sigma.quiz.presentation.api;

import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.dto.level.LevelRequest;
import com.sigma.quiz.domain.dto.level.LevelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/level")
public class LevelController {

    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping
    public ReturnReponse<LevelResponse> getLevels() {
        return levelUseCase.getLevels();
    }
    @PostMapping
    public ReturnReponse<LevelResponse> saveLevels(LevelRequest levelRequest) {
        return levelUseCase.saveLevels(levelRequest);
    }
}
