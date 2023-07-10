package com.sigma.quiz.application.usecase.impl;

import com.sigma.quiz.application.repository.ILevelRepository;
import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.Util;
import com.sigma.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LevelUseCase implements ILevelUseCase {
    @Autowired
    private ILevelRepository levelRepository;

    @Override
    public List<Level> getLevels() {
        List<Level> allLevels = levelRepository.findAll();
        return allLevels;
    }

    @Override
    public Level saveLevels(Level levelRequest) {
        levelRequest.setCreatedAt(new Date());
        levelRequest.setUpdatedAt(new Date());
        return levelRepository.save(levelRequest);
    }

    @Override
    public Level updateLevel(Level levelRequest) {
        Level dbLevel = levelRepository.findById(levelRequest.getId()).get();
        if (!Util.isNullOrWhiteSpace(levelRequest.getName())) {
            dbLevel.setName(levelRequest.getName());
        }
        dbLevel.setActive(levelRequest.isActive());
        return levelRepository.save(dbLevel);

    }

    @Override
    public Level deleteLevel(int id) {
        Level dbLevel = levelRepository.findById(id).get();
        levelRepository.delete(dbLevel);
        return dbLevel;
    }
}
