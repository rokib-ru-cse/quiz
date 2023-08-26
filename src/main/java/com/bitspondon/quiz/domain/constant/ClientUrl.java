package com.bitspondon.quiz.domain.constant;

public class ClientUrl {
    public static final String AUTH = Constant.CURRENT_API_VERSION + "auth";
    public static final String SIGNUP = Constant.CURRENT_API_VERSION + AUTH + "/signup";
    public static final String LOGIN = Constant.CURRENT_API_VERSION + AUTH + "/login";
    public static final String LEVEL = Constant.CURRENT_API_VERSION + "level";
    public static final String SUBJECT = Constant.CURRENT_API_VERSION + "subject";
    public static final String CHAPTER = Constant.CURRENT_API_VERSION + "chapter";
    public static final String QUESTION = Constant.CURRENT_API_VERSION + "question";
    public static final String OLD_QUIZ = Constant.CURRENT_API_VERSION + "oldquiz";
    public static final String OLD_QUIZ_START = "/start";
    public static final String OLD_QUIZ_SUBMIT = "/submit";
    public static final String LIVE_QUIZ = Constant.CURRENT_API_VERSION + "livequiz";
    public static final String LIVE_QUIZ_ENROLL = "/enroll";
    public static final String LIVE_QUIZ_START = "/start";
    public static final String LIVE_QUIZ_SUBMIT = "/submit";

}
