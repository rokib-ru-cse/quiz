package com.sigma.quiz.domain.dto.subject;

import com.sigma.quiz.domain.entities.Level;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class SubjectResponse {

    private int id;
    private String name;
    private String image;
    private String icon;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private Level level;
}
