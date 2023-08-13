package com.sigma.quiz.domain.entities;

import com.sigma.quiz.domain.dto.question.OptionDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull(message = "title is required")
    @Min(3)
    private String title;
    private String image;
    private String icon;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    private String description;

    private boolean isRadio;

    @ManyToOne
    private Level level;

    @Transient
    private int levelId;

    @ManyToOne
    private Subject subject;

    @Transient
    private int subjectId;

    @ManyToOne
    private Chapter chapter;

    @Transient
    private int chapterId;

    private String options; // 2 3 4 5 6 7
    private String answers; // 1 2
    @Transient

    private List<OptionDTO> optionList;
    @ManyToMany(mappedBy = "questions")
    private Set<Quiz> quizzes;

//    public Set<Quiz> getQuizzes() {
//        return quizzes;
//    }
}
