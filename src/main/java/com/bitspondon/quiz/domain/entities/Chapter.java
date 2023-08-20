package com.bitspondon.quiz.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "name is required")
    private String name;

    @Column(unique = true)
    @NotNull(message = "chapterCode is required")
    private String chapterCode;

    private String image;
    private String icon;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    private Subject subject;

    @Transient
    private Long subjectId;
}

