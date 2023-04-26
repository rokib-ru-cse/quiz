package com.sigma.quiz.domain.dto.level;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LevelResponse {
    private int id;
    private String name;
    private String image;
    private String icon;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;
}
