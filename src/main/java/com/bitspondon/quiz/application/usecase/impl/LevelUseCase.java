package com.bitspondon.quiz.application.usecase.impl;

import com.bitspondon.quiz.application.repository.ILevelRepository;
import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.domain.Util;
import com.bitspondon.quiz.domain.constant.ValidationMessage;
import com.bitspondon.quiz.domain.entities.Level;
import com.bitspondon.quiz.domain.exception.CustomException;
import com.bitspondon.quiz.domain.helper.LevelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public Level saveLevel(Level levelRequest) {
        levelRequest.setCreatedAt(new Date());
        levelRequest.setUpdatedAt(new Date());
        levelRequest.setLevelCode(LevelHelper.generateLevelCode(levelRepository, 1, null).get(0));
        return levelRepository.save(levelRequest);
    }

    @Override
    public List<Level> saveLevels(MultipartFile file) throws Exception {
        if (!Util.checkExcelFormat(file)) {
            throw new CustomException(ValidationMessage.EXCEL_FILE_FORMAT_NOT_MATCHED);
        }
        List<Level> levelList = LevelHelper.convertExcelToListOfLevel(file.getInputStream(), levelRepository);
        levelRepository.saveAll(levelList);
        return levelList;
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
