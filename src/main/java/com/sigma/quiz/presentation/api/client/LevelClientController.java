package com.sigma.quiz.presentation.api.client;

import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client/level")
public class LevelClientController {

    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping
    public ReturnReponse<Level> getLevels() {
        return levelUseCase.getLevels();
    }
}
