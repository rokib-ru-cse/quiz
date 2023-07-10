package com.sigma.quiz.presentation.api.client;

import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client/level")
public class LevelClientController {

    @Autowired
    private ILevelUseCase levelUseCase;

    @GetMapping
    public ReturnReponse<Level> getLevels() {
        List<Level> levels =  levelUseCase.getLevels();
        return ReturnReponse.<Level>builder().message("data found successfully").succeeded(true)
                .values(levels).build();
    }

    //    @PostMapping
//    public ReturnReponse<Level> saveLevel(Level levelRequest) {
//        Level savedLevel =  levelUseCase.saveLevels(levelRequest);
//        return ReturnReponse.<Level>builder().message("level saved successfully").succeeded(true).value(savedLevel).build();
//    }
//
//    @PutMapping
//    public ReturnReponse<Level> updateLevel(Level levelRequest) {
//        return levelUseCase.updateLevel(levelRequest);
//    }
//
//    @DeleteMapping
//    public ReturnReponse<Level> deleteLevel(@RequestParam int id) {
//        return levelUseCase.deleteLevel(id);
//    }
}
