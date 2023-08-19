package com.bitspondon.quiz.domain.helper;

import com.bitspondon.quiz.application.repository.ILevelRepository;
import com.bitspondon.quiz.domain.entities.Level;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LevelHelper {

    public static List<Level> convertExcelToListOfLevel(InputStream inputStream, ILevelRepository levelRepository) {
        List<Level> levelList = new ArrayList<>();
        List<String> levelCodeList;
        try {
            XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = sheets.getSheet("level");
            if (sheet == null) {
                System.out.println("Sheet 'level' not found.");
                return levelList;
            }
            // Constants for column indexes
            final int NAME_COLUMN = 0;
            final int IMAGE_COLUMN = 1;
            final int ICON_COLUMN = 2;
            final int ACTIVE_COLUMN = 3;

            int totalRows = sheet.getPhysicalNumberOfRows();
            String lastUsedLevelCode = null;
            levelCodeList = generateLevelCode(levelRepository, totalRows, lastUsedLevelCode);
            for (int rowIndex = 1; rowIndex < totalRows; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Level level = new Level();

                // Use the MissingCellPolicy to avoid checking for null cells
                Cell nameCell = row.getCell(NAME_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell imageCell = row.getCell(IMAGE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell iconCell = row.getCell(ICON_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell activeCell = row.getCell(ACTIVE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                level.setName(nameCell.getStringCellValue());
                level.setImage(imageCell.getStringCellValue());
                level.setIcon(iconCell.getStringCellValue());
                level.setActive(activeCell.getBooleanCellValue());
                level.setLevelCode(levelCodeList.get(rowIndex - 1));
                lastUsedLevelCode = levelCodeList.get(rowIndex - 1);
                level.setCreatedAt(new Date());
                level.setUpdatedAt(new Date());

                levelList.add(level);
            }
        } catch (Exception e) {

        }
        return levelList;
    }

    private static List<String> generateLevelCode(ILevelRepository levelRepository, int totalNeededCode, String lastLevelCode) {
        List<String> levelCodes = new ArrayList<>();

        if (lastLevelCode != null) {
            int lastCodeValue = Integer.parseInt(lastLevelCode.split("-")[1]);
            for (int i = 1; i <= totalNeededCode; i++) {
                String newCode = "L-" + (lastCodeValue + i);
                levelCodes.add(newCode);
            }
        } else {
            Level lastLevel = levelRepository.findTopByOrderByIdDesc();
            int startingValue = 1001; // Initial value for generating new codes

            if (lastLevel != null && lastLevel.getLevelCode() != null) {
                String lastCode = lastLevel.getLevelCode();
                startingValue = Integer.parseInt(lastCode.split("-")[1]) + 1;
            }

            for (int i = 0; i < totalNeededCode; i++) {
                String newCode = "L-" + (startingValue + i);
                levelCodes.add(newCode);
            }
        }

        return levelCodes;
    }


//    public static List<Level> convertExcelToListOfLevel(InputStream inputStream) {
//        List<Level> levelList = new ArrayList<>();
//        try {
//            XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
//            XSSFSheet sheet = sheets.getSheet("level");
//            if (sheet == null) {
//                System.out.println("Sheet 'level' not found.");
//                return levelList;
//            }
//
//
//
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) {
//                    continue; // Skip header row
//                }
//
//                Level level = new Level();
//
//                // Use the MissingCellPolicy to avoid checking for null cells
//                Cell idCell = row.getCell(ID_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                Cell nameCell = row.getCell(NAME_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                Cell imageCell = row.getCell(IMAGE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                Cell iconCell = row.getCell(ICON_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                Cell activeCell = row.getCell(ACTIVE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//
//                level.setId((long) idCell.getNumericCellValue());
//                level.setName(nameCell.getStringCellValue());
//                level.setImage(imageCell.getStringCellValue());
//                level.setIcon(iconCell.getStringCellValue());
//                level.setActive(activeCell.getBooleanCellValue());
//                level.setCreatedAt(new Date());
//                level.setUpdatedAt(new Date());
//
//                levelList.add(level);
//            }
//        } catch (Exception e) {
//            // Handle exceptions here
//        }
//        return levelList;
//    }
}
