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
//@Table(name = "feedback_reviews")
//public class FeedbackReview {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "quiz_id")
//    private com.sigma.quiz.domain.entities.QuizSubmission quizSubmission;
//
//    @Column(nullable = false)
//    private String feedbackText;
//
//    @Column(nullable = false)
//    private int rating; // Rating out of 5 or any other scale
//
//    // Constructors, getters, setters, and other methods
//
//    @Override
//    public String toString() {
//        return "FeedbackReview{" +
//                "id=" + id +
//                ", user=" + user +
//                ", quizSubmission=" + quizSubmission +
//                ", feedbackText='" + feedbackText + '\'' +
//                ", rating=" + rating +
//                '}';
//    }
//}


/**
 * Explanation:
 * <p>
 * The FeedbackReview class represents feedback and reviews provided by users for specific quiz submissions.
 * It has a many-to-one relationship with the User entity, representing the user who provided the feedback.
 * It also has a many-to-one relationship with the QuizSubmission entity, representing the quiz submission for which the feedback is provided.
 * The feedbackText field stores the actual feedback or review text.
 * The rating field represents the user's rating for the quiz submission, typically on a scale (e.g., out of 5).
 * This class allows you to capture user feedback and reviews for quiz submissions, helping to improve the quality of your quizzes and identify areas for enhancement.
 * <p>
 * As always, customize the class according to your application's requirements, and consider adding appropriate methods for querying and managing feedback and reviews.
 */