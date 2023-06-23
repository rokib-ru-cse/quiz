package com.sigma.quiz.application.usecase.impl;

import com.sigma.quiz.application.repository.ILevelRepository;
import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.Util;
import com.sigma.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelUseCase implements ILevelUseCase {
    @Autowired
    private ILevelRepository levelRepository;

    @Override
    public ReturnReponse<Level> getLevels() {
        List<Level> allLevels = levelRepository.findAll();
        return ReturnReponse.<Level>builder().message("data found successfully").succeeded(true)
                .values(allLevels).build();
    }

    @Override
    public ReturnReponse<Level> saveLevels(Level levelRequest) {
        Level savedLevel = levelRepository.save(levelRequest);
        return ReturnReponse.<Level>builder().message("level saved successfully").succeeded(true).value(savedLevel).build();
    }

    @Override
    public ReturnReponse<Level> updateLevel(Level levelRequest) {
        Level dbLevel = levelRepository.findById(levelRequest.getId()).get();
        if (!Util.isNullOrWhiteSpace(levelRequest.getName())) {
            dbLevel.setName(levelRequest.getName());
        }
        dbLevel.setActive(levelRequest.isActive());
        Level updatedLevel = levelRepository.save(dbLevel);
        return ReturnReponse.<Level>builder().message("level updated successfully").succeeded(true).value(updatedLevel).build();
    }

    @Override
    public ReturnReponse<Level> deleteLevel(int id) {
        Level dbLevel = levelRepository.findById(id).get();
        levelRepository.delete(dbLevel);
        return ReturnReponse.<Level>builder().message("level deleted successfully").succeeded(true).value(dbLevel).build();
    }
}
