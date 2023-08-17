package com.bitspondon.quiz.domain.constant;

public final class AdminUrl {


    public static class Level {
        public static final String INDEX = "/level/index";
        public static final String CREATE = "/level/create";
        public static final String EDIT = "/level/edit";
        public static final String REDIRECT_T0_INDEX = "redirect:" + INDEX;

    }

    public static class Subject {
        public static final String INDEX = "/subject/index";
        public static final String CREATE = "/subject/create";
        public static final String EDIT = "/subject/edit";
        public static final String REDIRECT_TO_INDEX = "redirect:" + INDEX;
    }


    public static class Chapter {
        public static final String INDEX = "/chapter/index";
        public static final String CREATE = "/chapter/create";
        public static final String EDIT = "/chapter/edit";
        public static final String REDIRECT_TO_INDEX = "redirect:" + INDEX;
    }
    public static class Question {
        public static final String INDEX = "/question/index";
        public static final String CREATE = "/question/create";
        public static final String EDIT = "/question/edit";
        public static final String REDIRECT_TO_INDEX = "redirect:" + INDEX;
    }

    public static class LiveQuiz {
        public static final String INDEX = "/livequiz/index";
        public static final String CREATE = "/livequiz/create";
        public static final String EDIT = "/livequiz/edit";
        public static final String REDIRECT_TO_INDEX = "redirect:" + INDEX;
    }

    public static class OldQuiz {
        public static final String INDEX = "/oldquiz/index";
        public static final String CREATE = "/oldquiz/create";
        public static final String EDIT = "/oldquiz/edit";
        public static final String REDIRECT_TO_INDEX = "redirect:" + INDEX;
    }
    public static class User {
        public static final String INDEX = "/user/index";
        public static final String CREATE = "/user/create";
        public static final String EDIT = "/user/edit";
        public static final String REDIRECT_TO_INDEX = "redirect:" + INDEX;
    }



}
