package com.bitspondon.quiz.domain.helper;

import com.bitspondon.quiz.application.repository.IChapterRepository;
import com.bitspondon.quiz.application.repository.ILevelRepository;
import com.bitspondon.quiz.application.repository.IQuestionRepository;
import com.bitspondon.quiz.application.repository.ISubjectRepository;
import com.bitspondon.quiz.domain.AllEnums;
import com.bitspondon.quiz.domain.entities.Chapter;
import com.bitspondon.quiz.domain.entities.Level;
import com.bitspondon.quiz.domain.entities.Question;
import com.bitspondon.quiz.domain.entities.Subject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

public class QuestionHelper {


    public static List<Question> convertExcelToListOfQuestions(InputStream inputStream, IQuestionRepository questionRepository, ILevelRepository levelRepository, ISubjectRepository subjectRepository, IChapterRepository chapterRepository) {
        List<Question> questionList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            List<Level> levelList = levelRepository.findAll();
            List<Subject> subjectList = subjectRepository.findAll();
            List<Chapter> chapterList = chapterRepository.findAll();

            Map<String, Level> levelMap = new HashMap<>();
            Map<String, Subject> subjectMap = new HashMap<>();
            Map<String, Chapter> chapterMap = new HashMap<>();

            String lastUsedQuestionCode = null;

            for (Level level : levelList) {
                levelMap.put(level.getLevelCode(), level);
            }
            for (Subject subject : subjectList) {
                subjectMap.put(subject.getSubjectCode(), subject);
            }
            for (Chapter chapter : chapterList) {
                chapterMap.put(chapter.getChapterCode(), chapter);
            }

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

                // Define column indexes
                final int TITLE_COLUMN = 0;
                final int DESCRIPTION_COLUMN = 1;
                final int IMAGE_COLUMN = 2;
                final int ICON_COLUMN = 3;
                final int IS_ACTIVE_COLUMN = 4;
                final int IS_RADIO_COLUMN = 5;
                final int DIFFICULTY_LEVEL_COLUMN = 6;
                final int LEVEL_CODE_COLUMN = 7;
                final int SUBJECT_CODE_COLUMN = 8;
                final int CHAPTER_CODE_COLUMN = 9;
                final int OPTIONS_COLUMN = 10;
                final int ANSWERS_COLUMN = 11;


                String levelCode = sheet.getRow(1).getCell(LEVEL_CODE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String subjectCode = sheet.getRow(1).getCell(SUBJECT_CODE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String chapterCode = sheet.getRow(1).getCell(CHAPTER_CODE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                Level level = levelMap.get(levelCode);
                Subject subject = subjectMap.get(subjectCode);
                Chapter chapter = chapterMap.get(chapterCode);

                if (level == null || subject == null || chapter == null) {
                    // Handle the case where the level is not found in the database
                    continue; // Skip processing this sheet
                }

                int totalRows = sheet.getPhysicalNumberOfRows();
                List<String> questionCodeList = generateQuestionCode(questionRepository, totalRows, lastUsedQuestionCode);


                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    Question question = new Question();

                    // Use the MissingCellPolicy to avoid checking for null cells
                    Cell titleCell = row.getCell(TITLE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if (titleCell.getStringCellValue().isEmpty()) {
                        break;
                    }

                    question.setTitle(titleCell.getStringCellValue());
                    question.setDescription(row.getCell(DESCRIPTION_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                    question.setImage(row.getCell(IMAGE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                    question.setIcon(row.getCell(ICON_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
                    question.setActive(row.getCell(IS_ACTIVE_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue());
                    question.setRadio(row.getCell(IS_RADIO_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue());
                    question.setDifficultyLevel(AllEnums.DifficultyLevelEnum.valueOf(row.getCell(DIFFICULTY_LEVEL_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().toUpperCase()));
                    question.setCreatedAt(new Date());
                    question.setUpdatedAt(new Date());
                    // ... set other entity fields
                    lastUsedQuestionCode = questionCodeList.get(rowIndex - 1);
                    question.setQuestionCode(lastUsedQuestionCode);

                    question.setLevel(level);
                    question.setSubject(subject);
                    question.setChapter(chapter);
                    question.setOptions(Arrays.asList(row.getCell(OPTIONS_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().split("\\|")));
                    question.setAnswers(Arrays.asList(row.getCell(ANSWERS_COLUMN, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().split("\\|")));
                    questionList.add(question);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }


    public static List<String> generateQuestionCode(IQuestionRepository questionRepository, int totalNeededCode, String lastQuestionCode) {
        List<String> questionCodes = new ArrayList<>();

        if (lastQuestionCode != null) {
            int lastCodeValue = Integer.parseInt(lastQuestionCode.split("-")[1]);
            for (int i = 1; i <= totalNeededCode; i++) {
                String newCode = "Q-" + (lastCodeValue + i);
                questionCodes.add(newCode);
            }
        } else {
            Question lastQuestion = questionRepository.findTopByOrderByIdDesc();
            int startingValue = 1001; // Initial value for generating new codes

            if (lastQuestion != null && lastQuestion.getQuestionCode() != null) {
                String lastCode = lastQuestion.getQuestionCode();
                startingValue = Integer.parseInt(lastCode.split("-")[1]) + 1;
            }

            for (int i = 0; i < totalNeededCode; i++) {
                String newCode = "Q-" + (startingValue + i);
                questionCodes.add(newCode);
            }
        }

        return questionCodes;
    }
}
