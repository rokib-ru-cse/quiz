package com.sigma.quiz.application.usecase.impl;

import com.sigma.quiz.application.repository.ILevelRepository;
import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.Util;
import com.sigma.quiz.domain.dto.level.LevelRequest;
import com.sigma.quiz.domain.dto.level.LevelResponse;
import com.sigma.quiz.domain.entities.Level;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LevelUseCase implements ILevelUseCase {
    @Autowired
    private ILevelRepository levelRepository;
    @Override
    public ReturnReponse<LevelResponse> getLevels() {
        List<Level> allLevels = levelRepository.findAll();
        List<LevelResponse> responseList = new ArrayList<>();
        for (Level level : allLevels) {
            LevelResponse newLevel = LevelResponse.builder()
                    .id(level.getId())
                    .name(level.getName())
                    .image(level.getImage())
                    .icon(level.getIcon())
                    .isActive(level.isActive())
                    .createdAt(level.getCreatedAt())
                    .updatedAt(level.getUpdatedAt())
                    .build();
            responseList.add(newLevel);
        }
        return ReturnReponse.<LevelResponse>builder().message("data found successfully").succeeded(true)
                .values(responseList).build();
    }

    @Override
    public ReturnReponse<LevelResponse> saveLevels(LevelRequest levelRequest) {
        Level newLevel = Level.builder()
                .name(levelRequest.getName())
                .image(levelRequest.getImage())
                .createdAt(new Date())
                .updatedAt(new Date())
                .isActive(levelRequest.isActive())
                .icon(levelRequest.getIcon())
                .build();
        Level savedLevel = levelRepository.save(newLevel);
        LevelResponse responseLevel = LevelResponse.builder()
                .id(savedLevel.getId())
                .name(levelRequest.getName())
                .image(levelRequest.getImage())
                .icon(levelRequest.getIcon())
                .isActive(levelRequest.isActive())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        return ReturnReponse.<LevelResponse>builder().message("level saved successfully").succeeded(true).value(responseLevel).build();
    }

    @Override
    public ReturnReponse<LevelResponse> updateLevel(LevelRequest levelRequest) {
        Level dbLevel = levelRepository.findById(levelRequest.getId()).get();
        if(!Util.isNullOrWhiteSpace(levelRequest.getName())){
            dbLevel.setName(levelRequest.getName());
        }

        Level savedLevel = levelRepository.save(dbLevel);
        LevelResponse responseLevel = LevelResponse.builder()
                .id(savedLevel.getId())
                .name(levelRequest.getName())
                .image(levelRequest.getImage())
                .icon(levelRequest.getIcon())
                .isActive(levelRequest.isActive())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        return ReturnReponse.<LevelResponse>builder().message("level saved successfully").succeeded(true).value(responseLevel).build();
    }

    @Override
    public ReturnReponse<LevelResponse> deleteLevel(int id) {
        return null;
    }
}
