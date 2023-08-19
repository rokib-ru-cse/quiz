//package com.bitspondon.quiz.domain.batchprocessing.level;
//
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.item.ItemStreamException;
//import org.springframework.batch.item.ItemStreamReader;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.io.InputStream;
//
//public class ExcelItemReader implements ItemStreamReader<Row> {
//
//    private final InputStreamResource resource;
//    private final int linesToSkip;
//    private final RowMapper<Row> rowMapper;
//
//    private int currentRowIndex = 0;
//
//    public ExcelItemReader(InputStream excelFile, int linesToSkip, RowMapper<Row> rowMapper) {
//        this.resource = new InputStreamResource(excelFile);
//        this.linesToSkip = linesToSkip;
//        this.rowMapper = rowMapper;
//    }
//
//    @Override
//    public Row read() throws Exception {
//        if (currentRowIndex < linesToSkip) {
//            for (int i = 0; i < linesToSkip; i++) {
//                resource.getInputStream().read();
//            }
//            currentRowIndex = linesToSkip;
//        }
//
//        try (XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream())) {
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            if (currentRowIndex > sheet.getLastRowNum()) {
//                return null; // No more rows to read
//            }
//            return sheet.getRow(currentRowIndex++);
//        }
//    }
//
//    @Override
//    public void open(ExecutionContext executionContext) throws ItemStreamException {
//        // Open any necessary resources here
//    }
//
//    @Override
//    public void update(ExecutionContext executionContext) throws ItemStreamException {
//        // Update any necessary state here
//    }
//
//    @Override
//    public void close() throws ItemStreamException {
//        // Close any opened resources here
//    }
//}
