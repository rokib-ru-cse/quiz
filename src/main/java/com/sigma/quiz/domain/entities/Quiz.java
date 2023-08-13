package com.sigma.quiz.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "quizzes")
public class Quiz {

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


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quiz_question",
            joinColumns = {@JoinColumn(name = "quiz_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")})
    private Set<Question> questions;
//    public Set<Question> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(Set<Question> questions) {
//        this.questions = questions;
//    }

}
