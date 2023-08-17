package com.bitspondon.quiz.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "oldquizzes")
public class OldQuiz {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "title is required")
    @Min(3)
    private String title;
    private String image;
    private String icon;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    private String description;

    private int marks;
    //duration in minutes
    private int duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.time.LocalDate quizDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private java.time.LocalTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private java.time.LocalTime endTime;

    private int totalParticipant;


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


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "oldquiz_question",
            joinColumns = {@JoinColumn(name = "quiz_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")})
    private Set<Question> questions;

}
