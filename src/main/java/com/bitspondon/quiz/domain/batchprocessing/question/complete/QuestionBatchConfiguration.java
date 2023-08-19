//package com.bitspondon.quiz.domain.batchprocessing.question.complete;
//
//import com.bitspondon.quiz.domain.entities.Question;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//
//
//@Configuration
//@EnableBatchProcessing
//public class QuestionBatchConfiguration {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    public EntityManagerFactory entityManagerFactory;
//
//    @Bean
//    public FlatFileItemReader<QuestionDTO> itemReader(@Value("${input.file}") Resource inputFile) {
//        return new FlatFileItemReaderBuilder<QuestionDTO>()
//                .name("questionItemReader")
//                .resource(inputFile)
//                .delimited()
//                .names(new String[]{"title", "description", "levelId", "subjectId"})
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//                    setTargetType(QuestionDTO.class);
//                }})
//                .build();
//    }
//
//    @Bean
//    public QuestionItemProcessor itemProcessor() {
//        return new QuestionItemProcessor();
//    }
//
//    @Bean
//    public JpaItemWriter<Question> itemWriter() {
//        JpaItemWriter<Question> writer = new JpaItemWriter<>();
//        writer.setEntityManagerFactory(entityManagerFactory);
//        return writer;
//    }
//
//    @Bean
//    public Step step1(ItemReader<QuestionDTO> itemReader, ItemProcessor<QuestionDTO, Question> itemProcessor, ItemWriter<Question> itemWriter) {
//        return stepBuilderFactory.get("step1")
//                .<QuestionDTO, Question>chunk(100)
//                .reader(itemReader)
//                .processor(itemProcessor)
//                .writer(itemWriter)
//                .build();
//    }
//
//    @Bean
//    public Job importJob(JobCompletionNotificationListener listener, Step step1) {
//        return jobBuilderFactory.get("importJob")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener)
//                .flow(step1)
//                .end()
//                .build();
//    }
//}
