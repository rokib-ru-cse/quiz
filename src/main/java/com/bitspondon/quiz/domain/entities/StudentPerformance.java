//package com.bitspondon.quiz.domain.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Table(name = "student_performance")
//public class StudentPerformance {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User student;
//
//    @ManyToOne
//    @JoinColumn(name = "quiz_id")
//    private com.sigma.quiz.domain.entities.QuizSubmission quizSubmission;
//
//    @Column(nullable = false)
//    private int score;
//
//    // Constructors, getters, setters, and other methods
//
//    @Override
//    public String toString() {
//        return "StudentPerformance{" +
//                "id=" + id +
//                ", student=" + student +
//                ", quizSubmission=" + quizSubmission +
//                ", score=" + score +
//                '}';
//    }
//}
