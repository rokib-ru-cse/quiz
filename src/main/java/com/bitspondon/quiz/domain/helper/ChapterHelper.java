package com.bitspondon.quiz.domain.helper;

import com.bitspondon.quiz.application.repository.IChapterRepository;
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


    public static List<Chapter> convertExcelToListOfChapters(InputStream inputStream, IChapterRepository chapterRepository, ISubjectRepository subjectRepository) {
        List<Chapter> chapterList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            List<Subject> subjectList = subjectRepository.findAll();
            String lastUsedChapterCode = null;
            Map<String, Subject> subjectMap = new HashMap<>();
            for (Subject subject : subjectList) {
                subjectMap.put(subject.getSubjectCode(), subject);
            }

            for (int subjectIndex = 0; subjectIndex < workbook.getNumberOfSheets(); subjectIndex++) {
                XSSFSheet sheet = workbook.getSheetAt(subjectIndex);
                String sheetName = sheet.getSheetName();

                final int NAME_COLUMN = 0;
                final int IMAGE_COLUMN = 1;
                final int ICON_COLUMN = 2;
                final int ACTIVE_COLUMN = 3;
                final int SUBJECT_CODE = 4;

                // Fetch the actual Subject object from the database based on subjectName
                Cell subjectCell = sheet.getRow(1).getCell(SUBJECT_CODE, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String subjectCode = subjectCell.getStringCellValue();
                Subject subject = subjectMap.get(subjectCode);
                if (subject == null) {
                    // Handle the case where the subject is not found in the database
                    continue; // Skip processing this sheet
                }

                int totalRows = sheet.getPhysicalNumberOfRows();
                List<String> chapterCodeList = generateChapterCode(chapterRepository, totalRows, lastUsedChapterCode);
                for (int rowIndex = 1; rowIndex < totalRows; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    Chapter chapter = new Chapter();

                    // Constants for column indexes

                    // Use the MissingCellPolicy to avoid checking for null cells
                    Cell nameCell = row.getCell(NAME_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell imageCell = row.getCell(IMAGE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell iconCell = row.getCell(ICON_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell activeCell = row.getCell(ACTIVE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    chapter.setName(nameCell.getStringCellValue());
                    chapter.setImage(imageCell.getStringCellValue());
                    chapter.setIcon(iconCell.getStringCellValue());
                    chapter.setActive(activeCell.getBooleanCellValue());
                    chapter.setCreatedAt(new Date());
                    chapter.setUpdatedAt(new Date());

                    lastUsedChapterCode = chapterCodeList.get(rowIndex - 1);
                    chapter.setChapterCode(lastUsedChapterCode);
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

    private static List<String> generateChapterCode(IChapterRepository chapterRepository, int totalNeededCode, String lastChapterCode) {
        List<String> chapterCodes = new ArrayList<>();

        if (lastChapterCode != null) {
            int lastCodeValue = Integer.parseInt(lastChapterCode.split("-")[1]);
            for (int i = 1; i <= totalNeededCode; i++) {
                String newCode = "C-" + (lastCodeValue + i);
                chapterCodes.add(newCode);
            }
        } else {
            Chapter lastChapter = chapterRepository.findTopByOrderByIdDesc();
            int startingValue = 1001; // Initial value for generating new codes

            if (lastChapter != null && lastChapter.getChapterCode() != null) {
                String lastCode = lastChapter.getChapterCode();
                startingValue = Integer.parseInt(lastCode.split("-")[1]) + 1;
            }

            for (int i = 0; i < totalNeededCode; i++) {
                String newCode = "C-" + (startingValue + i);
                chapterCodes.add(newCode);
            }
        }

        return chapterCodes;
    }

}
