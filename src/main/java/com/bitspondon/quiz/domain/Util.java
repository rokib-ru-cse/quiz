package com.bitspondon.quiz.domain;

import com.bitspondon.quiz.domain.constant.Constant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

    public static boolean isNullOrWhiteSpace(String data) {
        if (data == null) {
            return true;
        } else if ("".equals(data.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAdminRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(Constant.ROLE_ADMIN));
    }
}
