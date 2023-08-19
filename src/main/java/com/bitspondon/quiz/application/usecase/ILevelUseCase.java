package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.Level;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ILevelUseCase {
    Level getLevel(Long id);
    List<Level> getLevels();

    Level saveLevel(Level levelRequest);
    List<Level> saveLevels(MultipartFile file) throws Exception;

    Level updateLevel(Level levelRequest);

    Level deleteLevel(Long id);
}
