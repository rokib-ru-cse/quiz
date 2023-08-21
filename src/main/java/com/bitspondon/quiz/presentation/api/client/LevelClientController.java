package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.Level;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ClientUrl.LEVEL)
@PreAuthorize("hasAnyRole('" + Constant.ROLE_USER + "', '" + Constant.ROLE_ADMIN + "')")
public class LevelClientController {

    private final ILevelUseCase levelUseCase;

    public LevelClientController(ILevelUseCase levelUseCase) {
        this.levelUseCase = levelUseCase;
    }

    @GetMapping
    public ReturnReponse<Level> getLevels() {
        List<Level> levels = levelUseCase.getLevels();
        return ReturnReponse.<Level>builder().message("data found successfully").succeeded(true).values(levels).build();
    }
}
