//package com.bitspondon.quiz.domain.batchprocessing.question;
//
//import com.bitspondon.quiz.domain.entities.Question;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.excel.RowMapper;
//import org.springframework.batch.item.excel.poi.PoiItemReader;
//import org.springframework.core.io.Resource;
//
//public class ExcelQuestionItemReader implements ItemReader<Question> {
//
//    private final PoiItemReader<Question> delegate;
//
//    public ExcelQuestionItemReader(Resource resource, RowMapper<Question> rowMapper) {
//        delegate = new PoiItemReader<>();
//        delegate.setResource(resource);
//        delegate.setRowMapper(rowMapper);
//        delegate.setLinesToSkip(1); // Skip header row
//    }
//
//    @Override
//    public Question read() throws Exception {
//        return delegate.read();
//    }
//}
//
