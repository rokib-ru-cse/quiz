//package com.bitspondon.quiz.presentation.config;
//
//import com.bitspondon.quiz.domain.batchprocessing.level.ExcelItemReader;
//import com.bitspondon.quiz.domain.batchprocessing.level.LevelItemProcessor;
//import com.bitspondon.quiz.domain.batchprocessing.level.LevelItemWriter;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfig {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public ExcelItemReader excelItemReader() {
//        // Create and return ExcelItemReader instance
//    }
//
//    @Bean
//    public LevelItemProcessor levelItemProcessor() {
//        return new LevelItemProcessor();
//    }
//
//    @Bean
//    public LevelItemWriter levelItemWriter() {
//        return new LevelItemWriter();
//    }
//
//    @Bean
//    public Step excelFileToDatabaseStep() {
//        return stepBuilderFactory.get("excelFileToDatabaseStep")
//                .<Row, Level>chunk(10)
//                .reader(excelItemReader())
//                .processor(levelItemProcessor())
//                .writer(levelItemWriter())
//                .build();
//    }
//
//    @Bean
//    public Job excelFileToDatabaseJob() {
//        return jobBuilderFactory.get("excelFileToDatabaseJob")
//                .start(excelFileToDatabaseStep())
//                .build();
//    }
//}
//
