package com.bitspondon.quiz.domain.constant;

public final class AdminUrl {


    public static final String LEVEL_INDEX = "/level/index";
    public static final String LEVEL_CREATE = "/level/create";
    public static final String LEVEL_EDIT = "/level/edit";
    public static final String LEVEL_REDIRECT_TO_INDEX = "redirect:" + LEVEL_INDEX;

    public static final String SUBJECT_INDEX = "/subject/index";
    public static final String SUBJECT_CREATE = "/subject/create";
    public static final String SUBJECT_EDIT = "/subject/edit";
    public static final String SUBJECT_REDIRECT_TO_INDEX = "redirect:" + SUBJECT_INDEX;

    public static final String CHAPTER_INDEX = "/chapter/index";
    public static final String CHAPTER_CREATE = "/chapter/create";
    public static final String CHAPTER_EDIT = "/chapter/edit";
    public static final String CHAPTER_REDIRECT_TO_INDEX = "redirect:" + CHAPTER_INDEX;

    public static final String QUESTION_INDEX = "/question/index";
    public static final String QUESTION_CREATE = "/question/create";
    public static final String QUESTION_EDIT = "/question/edit";
    public static final String QUESTION_REDIRECT_TO_INDEX = "redirect:" + QUESTION_INDEX;

    public static final String LIVE_QUIZ_INDEX = "/livequiz/index";
    public static final String LIVE_QUIZ_CREATE = "/livequiz/create";
    public static final String LIVE_QUIZ_EDIT = "/livequiz/edit";
    public static final String LIVE_QUIZ_ASSIGN_QUESTION = "/livequiz/assignquestion";
    public static final String LIVE_QUIZ_DETAILS = "/livequiz/details";
    public static final String LIVE_QUIZ_ENROLL = "/livequiz/enroll";
    public static final String LIVE_QUIZ_REDIRECT_TO_INDEX = "redirect:" + LIVE_QUIZ_INDEX;
    public static final String LIVE_QUIZ_REDIRECT_TO_DETAILS = "redirect:" + LIVE_QUIZ_DETAILS;

    public static final String OLD_QUIZ_INDEX = "/oldquiz/index";
    public static final String OLD_QUIZ_CREATE = "/oldquiz/create";
    public static final String OLD_QUIZ_EDIT = "/oldquiz/edit";
    public static final String OLD_QUIZ_ASSIGN_QUESTION = "/oldquiz/assignquestion";
    public static final String OLD_QUIZ_DETAILS = "/oldquiz/details";
    public static final String OLD_QUIZ_REDIRECT_TO_INDEX = "redirect:" + OLD_QUIZ_INDEX;

    public static final String USER_INDEX = "/user/index";
    public static final String USER_CREATE = "/user/create";
    public static final String USER_EDIT = "/user/edit";
    public static final String USER_REDIRECT_TO_INDEX = "redirect:" + USER_INDEX;


}
