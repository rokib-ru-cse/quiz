//package com.bitspondon.quiz.domain.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
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
//    private QuizSubmission quizSubmission;
//
//    @Column(nullable = false)
//    private int score;
//
//    @Column(name = "submission_time")
//    private LocalDateTime submissionTimePercent;
//
//    /**
//     * 1 - 20 - 10- 50
//     *
//     * 2 - 30 - 50
//     *
//     * 30 - 50%
//     * 15
//     *
//     *
//     *
//     *
//     *
//     * */
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
