package com.bitspondon.quiz.domain.helper;

import com.bitspondon.quiz.application.repository.ILevelRepository;
import com.bitspondon.quiz.application.repository.ISubjectRepository;
import com.bitspondon.quiz.domain.entities.Level;
import com.bitspondon.quiz.domain.entities.Subject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

public class SubjectHelper {


    public static List<Subject> convertExcelToListOfSubjects(InputStream inputStream, ISubjectRepository subjectRepository, ILevelRepository levelRepository) {
        List<Subject> subjectList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            List<Level> levelList = levelRepository.findAll();
            Map<String, Level> levelMap = new HashMap<>();
            String lastUsedSubjectCode = null;
            for (Level level : levelList) {
                levelMap.put(level.getLevelCode(), level);
            }

            for (int levelIndex = 0; levelIndex <= workbook.getNumberOfSheets(); levelIndex++) {
                XSSFSheet sheet = workbook.getSheetAt(levelIndex);
                String sheetName = sheet.getSheetName();

                final int NAME_COLUMN = 0;
                final int IMAGE_COLUMN = 1;
                final int ICON_COLUMN = 2;
                final int ACTIVE_COLUMN = 3;
                final int LEVEL_CODE = 4;

                // Fetch the actual Level object from the database based on levelName
                Cell levelCell = sheet.getRow(1).getCell(LEVEL_CODE, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String levelCode = levelCell.getStringCellValue();
                //  Level level = levelList.stream().filter(item -> item.getId() == levelId).findFirst().orElse(null);
                Level level = levelMap.get(levelCode);
                if (level == null) {
                    // Handle the case where the level is not found in the database
                    continue; // Skip processing this sheet
                }

                int totalRows = sheet.getPhysicalNumberOfRows();
                List<String> subjectCodeList = generateSubjectCode(subjectRepository, totalRows, lastUsedSubjectCode);


                for (int rowIndex = 1; rowIndex < totalRows; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    Subject subject = new Subject();

                    // Constants for column indexes


                    // Use the MissingCellPolicy to avoid checking for null cells
                    Cell nameCell = row.getCell(NAME_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell imageCell = row.getCell(IMAGE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell iconCell = row.getCell(ICON_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell activeCell = row.getCell(ACTIVE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    if (nameCell.getStringCellValue().isEmpty()) {
                        break;
                    }
                    subject.setName(nameCell.getStringCellValue());
                    subject.setImage(imageCell.getStringCellValue());
                    subject.setIcon(iconCell.getStringCellValue());
                    subject.setActive(activeCell.getBooleanCellValue());
                    lastUsedSubjectCode = subjectCodeList.get(rowIndex - 1);
                    subject.setSubjectCode(lastUsedSubjectCode);
                    subject.setCreatedAt(new Date());
                    subject.setUpdatedAt(new Date());

                    // Set the level using the fetched Level object
                    subject.setLevel(level);
                    subjectList.add(subject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    private static List<String> generateSubjectCode(ISubjectRepository subjectRepository, int totalNeededCode, String lastSubjectCode) {
        List<String> subjectCodes = new ArrayList<>();

        if (lastSubjectCode != null) {
            int lastCodeValue = Integer.parseInt(lastSubjectCode.split("-")[1]);
            for (int i = 1; i <= totalNeededCode; i++) {
                String newCode = "S-" + (lastCodeValue + i);
                subjectCodes.add(newCode);
            }
        } else {
            Subject lastSubject = subjectRepository.findTopByOrderByIdDesc();
            int startingValue = 1001; // Initial value for generating new codes

            if (lastSubject != null && lastSubject.getSubjectCode() != null) {
                String lastCode = lastSubject.getSubjectCode();
                startingValue = Integer.parseInt(lastCode.split("-")[1]) + 1;
            }

            for (int i = 0; i < totalNeededCode; i++) {
                String newCode = "S-" + (startingValue + i);
                subjectCodes.add(newCode);
            }
        }

        return subjectCodes;
    }


}
