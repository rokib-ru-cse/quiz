package com.bitspondon.quiz.domain.constant;

import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.bitspondon.quiz.domain.dto.quizsubmission.QuizQuestionDTO;
import com.bitspondon.quiz.domain.entities.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Constant {
    // all constant
    public static final String API_VERSION_V1 = "/api/v1/";
    public static final String API_VERSION_V2 = "/api/v2/";
    public static final String API_VERSION_V3 = "/api/v3/";
    public static final String CURRENT_API_VERSION = API_VERSION_V1;
    public static final AdminUrl ADMIN_URL = new AdminUrl();
    public static final ClientUrl CLIENT_URL = new ClientUrl();
    // used for entity
    public static final String CONSTANTS = "constants";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String LEVEL_ID = "levelId";
    public static final String LEVEL = "level";
    public static final String SUBJECT_ID = "subjectId";
    public static final String SUBJECT = "subject";
    public static final String CHAPTER_ID = "chapterId";
    public static final String CHAPTER = "chapter";
    public static final String CHAPTER_LIST = "chapterList";
    public static final String QUESTION_ID = "questionId";
    public static final String QUESTION = "question";
    public static final String QUESTION_OPTIONS = "options";
    public static final String QUESTION_LIST = "questionList";
    public static final String OLD_QUIZ_ID = "oldQuizId";
    public static final String OLD_QUIZ = "oldQuiz";
    public static final String OLD_QUIZ_LIST = "oldQuizList";
    public static final String LIVE_QUIZ_ID = "liveQuizId";
    public static final String LIVE_QUIZ = "liveQuiz";
    public static final String LIVE_QUIZ_LIST = "liveQuizList";


    //excel section
    public static final String XLS_FORMAT = "application/vnd.ms-excel";
    public static final String XLSX_FORMAT = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String LEVEL_EXCEL_FILE_NAME = "level";
    public static final String EXCEL_FILE_TRUE = "true";


    // used in different section
    public static final String MULTIPART_FILE_REQUEST_PARAM_NAME = "file";


    // all objects and list

    private String actionUrl = "";

    private List<Level> levelList = null;
    private List<Subject> subjectList = null;
    private List<Chapter> chapterList = null;
    private List<Question> questionList = null;
    private List<QuizQuestionDTO> quizQuestionList = null;
    private List<OptionDTO> questionOptions = null;
    private List<OldQuiz> oldQuizList = null;
    private List<LiveQuiz> liveQuizList = null;
}
