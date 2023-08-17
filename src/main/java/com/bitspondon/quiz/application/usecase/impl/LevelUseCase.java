package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.application.repository.ILevelRepository;
import com.bitspondon.quiz.domain.Util;
import com.bitspondon.quiz.domain.entities.Level;
import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LevelUseCase implements ILevelUseCase {
    @Autowired
    private ILevelRepository levelRepository;

    @Override
    public Level getLevel(Long id) {
        return levelRepository.findById(id).get();
    }

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
    public Level deleteLevel(Long id) {
        Level dbLevel = levelRepository.findById(id).get();
        levelRepository.delete(dbLevel);
        return dbLevel;
    }
}
