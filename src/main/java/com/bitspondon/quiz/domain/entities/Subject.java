package com.bitspondon.quiz.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "name is required")
    private String name;

    @Column(unique = true)
    @NotNull(message = "subjectCode is required")
    private String subjectCode;

    private String image;
    private String icon;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JsonIgnore
    private Level level;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "subject")
    private Set<Chapter> chapterList;

    @Transient
    private long levelId;
}
