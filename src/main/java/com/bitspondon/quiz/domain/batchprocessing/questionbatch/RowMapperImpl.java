//package com.bitspondon.quiz.domain.batchprocessing.questionbatch;
//
//import com.bitspondon.quiz.domain.entities.Question;
//import org.springframework.batch.extensions.excel.RowMapper;
//import org.springframework.batch.extensions.excel.support.rowset.RowSet;
//
//public class RowMapperImpl implements RowMapper<Question> {
//    public RowMapperImpl() {
//    }
//
//    @Override
//    public Question mapRow(RowSet rs) throws Exception {
//        if (rs == null || rs.getCurrentRow() == null) {
//            return null;
//        }
//        Question question = new Question();
//        question.setTitle(rs.getCellValue(0));
//        question.setDescription(rs.getColumnValue(0));
//
//        return question;
//    }
//
//}