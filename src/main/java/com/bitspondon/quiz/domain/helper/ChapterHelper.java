package com.bitspondon.quiz.domain.helper;

import com.bitspondon.quiz.application.repository.ISubjectRepository;
import com.bitspondon.quiz.domain.entities.Chapter;
import com.bitspondon.quiz.domain.entities.Subject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

public class ChapterHelper {


    public static List<Chapter> convertExcelToListOfChapters(InputStream inputStream, ISubjectRepository subjectRepository) {
        List<Chapter> chapterList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            List<Subject> subjectList = subjectRepository.findAll();
            Map<Long, Subject> subjectMap = new HashMap<>();
            for (Subject subject : subjectList) {
                subjectMap.put(subject.getId(), subject);
            }

            for (int subjectIndex = 0; subjectIndex < workbook.getNumberOfSheets(); subjectIndex++) {
                XSSFSheet sheet = workbook.getSheetAt(subjectIndex);
                String subjectName = sheet.getSheetName();

                final int ID_COLUMN = 0;
                final int NAME_COLUMN = 1;
                final int IMAGE_COLUMN = 2;
                final int ICON_COLUMN = 3;
                final int ACTIVE_COLUMN = 4;
                final int SUBJECT_ID = 5;

                // Fetch the actual Subject object from the database based on subjectName
                Cell subjectCell = sheet.getRow(1).getCell(SUBJECT_ID, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                long subjectId = (long) subjectCell.getNumericCellValue();
                Subject subject = subjectMap.get(subjectId);
                if (subject == null) {
                    // Handle the case where the subject is not found in the database
                    // You might want to log a warning or take appropriate action
                    continue; // Skip processing this sheet
                }

                int totalRows = sheet.getPhysicalNumberOfRows();

                for (int rowIndex = 1; rowIndex < totalRows; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    Chapter chapter = new Chapter();

                    // Constants for column indexes

                    // Use the MissingCellPolicy to avoid checking for null cells
                    Cell idCell = row.getCell(ID_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell nameCell = row.getCell(NAME_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell imageCell = row.getCell(IMAGE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell iconCell = row.getCell(ICON_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell activeCell = row.getCell(ACTIVE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    chapter.setId((long) idCell.getNumericCellValue());
                    chapter.setName(nameCell.getStringCellValue());
                    chapter.setImage(imageCell.getStringCellValue());
                    chapter.setIcon(iconCell.getStringCellValue());
                    chapter.setActive(activeCell.getBooleanCellValue());
                    chapter.setCreatedAt(new Date());
                    chapter.setUpdatedAt(new Date());

                    // Set the subject using the fetched Subject object
                    chapter.setSubject(subject);

                    chapterList.add(chapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapterList;
    }
}
