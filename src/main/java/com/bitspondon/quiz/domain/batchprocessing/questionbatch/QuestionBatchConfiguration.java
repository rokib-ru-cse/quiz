//package com.bitspondon.quiz.domain.batchprocessing.questionbatch;
//
//import com.bitspondon.quiz.domain.entities.Question;
//import org.springframework.batch.extensions.excel.poi.PoiItemReader;
//import org.springframework.batch.item.ItemStreamReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.ClassPathResource;
//
//public class QuestionBatchConfiguration {
//
//
//
//    @Bean
//    ItemStreamReader<Question> reader() {
//        PoiItemReader reader = new PoiItemReader();
//        reader.setResource(new ClassPathResource("input.xlsx"));
//        reader.setRowMapper(new RowMapperImpl());
//        reader.setLinesToSkip(1);
//
//        return reader;
//    }
//
//    public class RowMapperImpl implements RowMapper<BookList> {
//        public RowMapperImpl() {
//        }
//
//        @Override
//        public Question mapRow(RowSet rs) throws Exception {
//            if (rs == null || rs.getCurrentRow() == null) {
//                return null;
//            }
//            BookList bl = new BookList();
//            bl.setAuthor(rs.getColumnValue(1));
//            bl.setBookName(rs.getColumnValue(0));
//
//            return bl;
//        }
//
//    }
//
//
//}
