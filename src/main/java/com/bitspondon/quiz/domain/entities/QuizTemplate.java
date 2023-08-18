//package com.bitspondon.quiz.domain.entities;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Entity
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Table(name = "quiz_templates")
//public class QuizTemplate {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @ManyToOne
//    @JoinColumn(name = "subject_id")
//    private Subject subject;
//
//    @ManyToOne
//    @JoinColumn(name = "chapter_id")
//    private Chapter chapter;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private DifficultyLevelEnum difficultyLevel;
//
//    @OneToMany(mappedBy = "quizTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<QuizTemplateQuestion> templateQuestions;
//
//    // Constructors, getters, setters, and other methods
//
//    @Override
//    public String toString() {
//        return "QuizTemplate{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", subject=" + subject +
//                ", chapter=" + chapter +
//                ", difficultyLevel=" + difficultyLevel +
//                '}';
//    }
//}
//
//
//

/**
 * A QuizTemplate table serves as a blueprint or a pre-defined structure for creating quizzes. It's used to store configurations and settings that define how a quiz should be composed. Here are some use cases for having a QuizTemplate table:
 *
 * Standardized Quiz Creation:
 * If you have a consistent format for creating quizzes, a template can help ensure that all quizzes adhere to the same structure. This is particularly useful when you're dealing with a large number of quizzes across different subjects, levels, or topics.
 *
 * Efficient Quiz Generation:
 * Creating quizzes from scratch can be time-consuming. With templates, you can pre-define the questions, subjects, chapters, difficulty levels, and other settings. When it's time to create a new quiz, you can use the template as a starting point, reducing manual effort.
 *
 * Consistent Difficulty Level Distribution:
 * Templates allow you to ensure that quizzes have a balanced distribution of difficulty levels. For example, you might want each quiz to have a mix of easy, medium, and hard questions. Templates can help you achieve this consistency.
 *
 * Ensuring Coverage of Topics:
 * If you have a broad curriculum or subject matter to cover, templates can help ensure that quizzes include questions from various topics or chapters. This prevents inadvertently leaving out important topics.
 *
 * Ease of Customization:
 * While templates provide a predefined structure, they can also be customized as needed. For instance, you might have a general template for biology quizzes, but you can modify it slightly to create a quiz specifically focused on genetics.
 *
 * Bulk Quiz Generation:
 * Templates are especially useful when generating a batch of quizzes for different classes or time periods. You can generate multiple quizzes quickly by applying the same template with minor variations.
 *
 * Quality Control:
 * With standardized templates, you can implement quality control measures more effectively. You can review and approve templates before they are used to create quizzes, ensuring accuracy and consistency.
 *
 * Educational Standardization:
 * In educational institutions, quizzes might need to meet certain standards. Templates can help meet these standards by ensuring that quizzes are well-structured and aligned with educational objectives.
 *
 * Overall, the QuizTemplate table allows you to streamline the process of quiz creation, maintain consistency, and manage quizzes efficiently across different subjects, topics, and levels. It's a powerful tool for educators, curriculum designers, and administrators who want to provide standardized and high-quality quizzes to their students
 *
 *
 *
 *
 *
 *
 *
 * */