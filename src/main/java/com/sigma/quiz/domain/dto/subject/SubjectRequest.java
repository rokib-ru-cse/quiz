package com.sigma.quiz.domain.dto.subject;

import com.sigma.quiz.domain.entities.Level;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class SubjectRequest {
    private String name;
    private String image;
    private String icon;
    private boolean isActive;
    private int levelId;
}
