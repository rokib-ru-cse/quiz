//package com.bitspondon.quiz.domain.batchprocessing.question;
//
//
//import com.bitspondon.quiz.application.repository.IQuestionRepository;
//import com.bitspondon.quiz.domain.entities.Question;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.data.RepositoryItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.LineMapper;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class QuestionBatchConfig {
//
//    @Autowired
//    private IQuestionRepository questionRepository;
//
//
////    @Bean
////    @StepScope
////    public FlatFileItemReader<Question> reader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile) {
////        FlatFileItemReader<Question> itemReader = new FlatFileItemReader<>();
////        itemReader.setResource(new FileSystemResource(new File(pathToFile)));
////        itemReader.setName("questionReader");
////        itemReader.setLinesToSkip(1);
////        itemReader.setLineMapper(lineMapper());
////        return itemReader;
////    }
//
////    @Bean
////    @StepScope
////    public ExcelItemReader<Question> excelReader(@Value("#{jobParameters[excelFilePath]}") String excelFilePath) {
////        ExcelItemReader<Question> excelItemReader = new ExcelItemReader<>();
////        excelItemReader.setResource(new FileSystemResource(excelFilePath));
////        excelItemReader.setRowMapper(new BeanWrapperRowMapper<>(Question.class));
////        excelItemReader.setSheetName("Sheet1"); // Change to your actual sheet name
////        return excelItemReader;
////    }
//
//    private LineMapper<Question> lineMapper() {
//        DefaultLineMapper<Question> lineMapper = new DefaultLineMapper<>();
//
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        lineTokenizer.setDelimiter(",");
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames("id", "title", "image", "icon", "isActive", "createdAt", "updatedAt",
//                "description", "isRadio", "levelId", "subjectId", "chapterId", "difficultyLevel", "options", "answers");
//
//        BeanWrapperFieldSetMapper<Question> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(Question.class);
//
//        lineMapper.setLineTokenizer(lineTokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//        return lineMapper;
//    }
//
//
//    @Bean
//    public QuestionProcessor processor() {
//        return new QuestionProcessor();
//    }
//
//    @Bean
//    public RepositoryItemWriter<Question> writer() {
//        RepositoryItemWriter<Question> writer = new RepositoryItemWriter<>();
//        writer.setRepository(questionRepository);
//        writer.setMethodName("save");
//        return writer;
//    }
//
//
//    @Bean
//    public Step step1(JobRepository repository, PlatformTransactionManager manager, FlatFileItemReader<Question> reader) {
//        return new StepBuilder("question-step", repository).<Question, Question>chunk(10, manager).reader(reader).processor(processor()) //optional
//                .writer(writer()).taskExecutor(taskExecutor()).build();
//
//    }
//
//    @Bean
//    public Job runJob(JobRepository repository, PlatformTransactionManager manager, FlatFileItemReader<Question> reader) {
//        return new JobBuilder("importQuestions", repository).flow(step1(repository, manager, reader)).end().build();
//
//    }
//
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        asyncTaskExecutor.setConcurrencyLimit(10);
//        return asyncTaskExecutor;
//    }
//}
