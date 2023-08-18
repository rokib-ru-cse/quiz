package com.bitspondon.quiz.domain.constant;

import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.bitspondon.quiz.domain.entities.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Constant {
    // all constant
    public static final AdminUrl ADMIN_URL = new AdminUrl();
    public static final String CONSTANTS = "constants";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
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
    // all objects and list

    private String actionUrl = "";
    private List<Level> levelList = null;
    private List<Subject> subjectList = null;
    private List<Chapter> chapterList = null;
    private List<Question> questionList = null;
    private List<OptionDTO> questionOptions = null;
    private List<OldQuiz> oldQuizList = null;
    private List<LiveQuiz> liveQuizList= null;
}
