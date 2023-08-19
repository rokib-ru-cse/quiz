//package com.bitspondon.quiz.domain.batchprocessing.level;
//
//import com.bitspondon.quiz.domain.entities.Level;
//import org.apache.poi.ss.usermodel.Row;
//import org.springframework.batch.item.ItemProcessor;
//
//import java.util.Date;
//
//public class LevelItemProcessor implements ItemProcessor<Row, Level> {
//    @Override
//    public Level process(Row row) {
//        Level level = new Level();
//        level.setId((long) row.getCell(0).getNumericCellValue());
//        level.setName(row.getCell(1).getStringCellValue());
//        level.setImage(row.getCell(2).getStringCellValue());
//        level.setIcon(row.getCell(3).getStringCellValue());
//        level.setActive(row.getCell(4).getBooleanCellValue());
//        level.setCreatedAt(new Date());
//        level.setUpdatedAt(new Date());
//        return level;
//    }
//}
