package com.bitspondon.quiz.domain.exception;

import com.bitspondon.quiz.domain.Util;

public class CustomException extends Exception {
    public CustomException(String message) throws ApiException, AdminException {
        if (Util.isAdminRequest()) {
            throw new AdminException(message);
        } else {
            throw new ApiException(message);
        }
    }
}
