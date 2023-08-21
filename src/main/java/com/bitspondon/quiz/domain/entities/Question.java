package com.bitspondon.quiz.domain.entities;

import com.bitspondon.quiz.domain.AllEnums;
import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "title is required")
    private String title;
    @Column(unique = true)
    @Nullable
    private String questionCode;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private AllEnums.DifficultyLevelEnum difficultyLevel; // This field stores the difficulty level as an enum

    @Column(columnDefinition = "TEXT")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String options; // Store as JSON array ["Option A", "Option B", "Option C", "Option D"]


    @Column(columnDefinition = "TEXT")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String answers; // Store as JSON array ["Option A", "Option B"]

    @Transient
    private List<OptionDTO> optionList;
    @JsonBackReference
    @ManyToMany(mappedBy = "questions", fetch = FetchType.LAZY)
    private Set<OldQuiz> oldQuizs = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "questions", fetch = FetchType.LAZY)
    private Set<LiveQuiz> liveQuizs = new HashSet<>();

    public List<String> getOptions() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(options, List.class);
        } catch (JsonProcessingException e) {
            // Handle the exception
            return null;
        }
    }

    public void setOptions(List<String> options) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.options = objectMapper.writeValueAsString(options);
        } catch (JsonProcessingException e) {
            // Handle the exception
            this.options = null;
        }
    }

    public List<String> getAnswers() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(answers, List.class);
        } catch (JsonProcessingException e) {
            // Handle the exception
            return null;
        }
    }

    public void setAnswers(List<String> answers) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.answers = objectMapper.writeValueAsString(answers);
        } catch (JsonProcessingException e) {
            // Handle the exception
            this.answers = null;
        }
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", title='" + title + '\'' + '}';
    }
}


