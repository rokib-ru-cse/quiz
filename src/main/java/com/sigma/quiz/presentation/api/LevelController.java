package com.sigma.quiz.presentation.api;

import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.dto.level.LevelRequest;
import com.sigma.quiz.domain.dto.level.LevelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ReturnReponse<LevelResponse> saveLevel(LevelRequest levelRequest) {
        return levelUseCase.saveLevels(levelRequest);
    }
    @PutMapping
    public ReturnReponse<LevelResponse> updateLevel(LevelRequest levelRequest) {
        return levelUseCase.updateLevel(levelRequest);
    }
    @DeleteMapping
    public ReturnReponse<LevelResponse> deleteLevel(@RequestParam int id) {
        return levelUseCase.deleteLevel(id);
    }
}
