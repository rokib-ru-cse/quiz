package com.bitspondon.quiz.domain.entities;

import com.bitspondon.quiz.domain.AllEnums;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "livequizzes")
public class LiveQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "title is required")
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private AllEnums.DifficultyLevelEnum difficultyLevel;

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
    @JoinTable(name = "livequiz_question",
            joinColumns = {@JoinColumn(name = "livequiz_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")})
    private Set<Question> questions = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "livequiz_user",
            joinColumns = {@JoinColumn(name = "livequiz_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Set<User> users = new HashSet<>();

}
