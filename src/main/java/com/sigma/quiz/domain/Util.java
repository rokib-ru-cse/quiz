package com.sigma.quiz.domain;

public class Util {

    public static boolean isNullOrWhiteSpace(String data) {
        if (data == null) {
            return true;
        } else if (data.trim() == "") {
            return true;
        } else {
            return false;
        }
    }
}
