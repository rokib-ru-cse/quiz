//package com.bitspondon.quiz.domain.batchprocessing.question;
//
//
//import com.bitspondon.quiz.application.repository.IQuestionRepository;
//import com.bitspondon.quiz.domain.entities.Question;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
//import org.springframework.batch.item.file.transform.LineTokenizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.FileSystemResource;
//
//import java.io.File;
//import java.util.List;
//
//public class UploadQuestionsJob {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private IQuestionRepository questionRepository;
//
//    @Bean
//    public Job uploadQuestionsJob() {
//        return jobBuilderFactory.get("uploadQuestionsJob")
//                .incrementer(new RunIdIncrementer())
//                .start(uploadQuestionsStep())
//                .build();
//    }
//
//    @Bean
//    public Step uploadQuestionsStep() {
//        return stepBuilderFactory.get("uploadQuestionsStep")
//                .<Question, Question>chunk(1000)
//                .reader(questionItemReader())
//                .writer(questionItemWriter())
//                .build();
//    }
//
//    @Bean
//    public ItemReader<Question> questionItemReader() {
//        FlatFileItemReader<Question> reader = new FlatFileItemReader<>();
//        reader.setResource(new FileSystemResource(new File("questions.xlsx")));
//
//        DefaultLineMapper<Question> lineMapper = new DefaultLineMapper<>();
//
//        BeanWrapperFieldExtractor<Question> fieldExtractor = new BeanWrapperFieldExtractor<>();
//        fieldExtractor.setBeanClass(Question.class);
//        fieldExtractor.setProperties(new String[]{"title", "options", "answers"});
//
//        lineMapper.setLineTokenizer(new LineTokenizer());
//        lineMapper.setFieldExtractor(fieldExtractor);
//
//        reader.setLineMapper(lineMapper);
//
//        return reader;
//    }
//
//    @Bean
//    public ItemWriter<Question> questionItemWriter() {
//        return new ItemWriter<Question>() {
//            @Override
//            public void write(Chunk<? extends Question> chunk) throws Exception {
//                questionRepository.saveAll(chunk);
//            }
//
////            @Override
////            public void write(List<? extends Question> items) throws Exception {
////                questionRepository.saveAll(items);
////            }
//        };
//    }
//}
