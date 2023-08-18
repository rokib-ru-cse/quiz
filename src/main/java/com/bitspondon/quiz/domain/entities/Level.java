package com.bitspondon.quiz.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "name is required")
    @Min(3)
    private String name;
    private String image;
    private String icon;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;
}
