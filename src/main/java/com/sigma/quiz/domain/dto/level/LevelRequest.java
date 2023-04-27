package com.sigma.quiz.domain.dto.level;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LevelRequest {
    private int id;
    private String name;
    private String image;
    private String icon;
    private boolean isActive;
}
