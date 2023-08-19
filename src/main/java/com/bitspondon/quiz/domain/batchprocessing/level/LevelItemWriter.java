package com.bitspondon.quiz.domain.batchprocessing.level;

import com.bitspondon.quiz.application.repository.ILevelRepository;
import com.bitspondon.quiz.domain.entities.Level;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LevelItemWriter implements ItemWriter<Level> {

    @Autowired
    private ILevelRepository levelRepository;

    @Override
    public void write(Chunk<? extends Level> chunk) throws Exception {
        levelRepository.saveAll(chunk);
    }
}

