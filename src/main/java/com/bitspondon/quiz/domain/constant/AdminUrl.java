package com.bitspondon.quiz.domain.constant;

public final class AdminUrl {


    public static final String ADMIN = "/admin/**";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String LEVEL_INDEX = "/admin/level/index";
    public static final String LEVEL_CREATE = "/admin/level/create";
    public static final String LEVEL_UPLOAD = "/admin/level/upload";
    public static final String LEVEL_EDIT = "/admin/level/edit";
    public static final String LEVEL_DELETE = "/admin/level/delete";
    public static final String LEVEL_REDIRECT_TO_INDEX = "redirect:" + LEVEL_INDEX;

    public static final String SUBJECT_INDEX = "/admin/subject/index";
    public static final String SUBJECT_CREATE = "/admin/subject/create";
    public static final String SUBJECT_UPLOAD = "/admin/subject/upload";

    public static final String SUBJECT_EDIT = "/admin/subject/edit";
    public static final String SUBJECT_REDIRECT_TO_INDEX = "redirect:" + SUBJECT_INDEX;

    public static final String CHAPTER_INDEX = "/admin/chapter/index";
    public static final String CHAPTER_CREATE = "/admin/chapter/create";
    public static final String CHAPTER_UPLOAD = "/admin/chapter/upload";
    public static final String CHAPTER_EDIT = "/admin/chapter/edit";
    public static final String CHAPTER_REDIRECT_TO_INDEX = "redirect:" + CHAPTER_INDEX;

    public static final String QUESTION_INDEX = "/admin/question/index";
    public static final String QUESTION_CREATE = "/admin/question/create";
    public static final String QUESTION_UPLOAD = "/admin/question/upload";
    public static final String QUESTION_EDIT = "/admin/question/edit";
    public static final String QUESTION_REDIRECT_TO_INDEX = "redirect:" + QUESTION_INDEX;

    public static final String LIVE_QUIZ_INDEX = "/admin/livequiz/index";
    public static final String LIVE_QUIZ_CREATE = "/admin/livequiz/create";
    public static final String LIVE_QUIZ_EDIT = "/admin/livequiz/edit";
    public static final String LIVE_QUIZ_ASSIGN_QUESTION = "/admin/livequiz/assignquestion";
    public static final String LIVE_QUIZ_DETAILS = "/admin/livequiz/details";
    public static final String LIVE_QUIZ_ENROLL = "/admin/livequiz/enroll";
    public static final String LIVE_QUIZ_START = "/admin/livequiz/start";
    public static final String LIVE_QUIZ_REDIRECT_TO_INDEX = "redirect:" + LIVE_QUIZ_INDEX;
    public static final String LIVE_QUIZ_REDIRECT_TO_DETAILS = "redirect:" + LIVE_QUIZ_DETAILS;

    public static final String OLD_QUIZ_INDEX = "/admin/oldquiz/index";
    public static final String OLD_QUIZ_CREATE = "/admin/oldquiz/create";
    public static final String OLD_QUIZ_EDIT = "/admin/oldquiz/edit";
    public static final String OLD_QUIZ_ASSIGN_QUESTION = "/admin/oldquiz/assignquestion";
    public static final String OLD_QUIZ_DETAILS = "/admin/oldquiz/details";
    public static final String OLD_QUIZ_REDIRECT_TO_INDEX = "redirect:" + OLD_QUIZ_INDEX;

    public static final String USER_INDEX = "/admin/user/index";
    public static final String USER_CREATE = "/admin/user/create";
    public static final String USER_EDIT = "/admin/user/edit";
    public static final String USER_REDIRECT_TO_INDEX = "redirect:" + USER_INDEX;


}
