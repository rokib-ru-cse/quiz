package com.sigma.quiz.presentation.api.admin;

import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/level")
public class LevelAdminController {

    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping
    public ReturnReponse<Level> getLevels() {
        return levelUseCase.getLevels();
    }
    @PostMapping
    public ReturnReponse<Level> saveLevel(Level levelRequest) {
        return levelUseCase.saveLevels(levelRequest);
    }
    @PutMapping
    public ReturnReponse<Level> updateLevel(Level levelRequest) {
        return levelUseCase.updateLevel(levelRequest);
    }
    @DeleteMapping
    public ReturnReponse<Level> deleteLevel(@RequestParam int id) {
        return levelUseCase.deleteLevel(id);
    }
}