package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.entities.Level;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ClientUrl.LEVEL)
@AllArgsConstructor
public class LevelClientController {

    private final ILevelUseCase levelUseCase;

    @GetMapping
    public ReturnReponse<List<Level>> getLevels() {
        List<Level> levels = levelUseCase.getLevels();
        return ReturnReponse.<List<Level>>builder().message("data found successfully").succeeded(true).value(levels).build();
    }
}
