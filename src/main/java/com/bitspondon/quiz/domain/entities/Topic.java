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
//@Table(name = "topics")
//public class Topic {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @ManyToOne
//    @JoinColumn(name = "chapter_id")
//    private Chapter chapter;
//
//    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Question> questions;
//
//    // Constructors, getters, setters, and other methods
//
//    @Override
//    public String toString() {
//        return "Topic{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", chapter=" + chapter +
//                '}';
//    }
//}


/**
 * why we need topics table?
 *
 * The concept of a "topics" table can be quite useful in an educational quiz system, particularly when dealing with a large number of questions that cover different areas within a subject or chapter. Here's why you might consider having a separate "topics" table:
 *
 * Organizing Questions: A topics table allows you to categorize questions based on specific areas of knowledge or subtopics within a subject. This makes it easier to manage and retrieve questions related to specific subjects or chapters.
 *
 * Granularity: By organizing questions into topics, you can provide a higher level of granularity in your quiz system. This can help users focus on specific areas they want to study or be tested on.
 *
 * Curriculum Alignment: If your quiz system is aligned with educational curricula, having topics can help you align questions with the specific topics or subtopics covered in a curriculum.
 *
 * Flexibility: Topics provide flexibility in content organization. As subjects and chapters evolve or expand, you can easily add new topics without changing the overall structure of your application.
 *
 * User-Focused: From a user's perspective, having questions categorized by topics allows them to choose and study specific areas of interest or weakness.
 *
 * Better Question Selection: When generating quizzes, having topics allows you to select questions from specific areas, creating a more balanced and comprehensive quiz.
 *
 * Reporting and Analytics: With topics, you can analyze user performance on different subtopics. This helps provide insights into areas where users might excel or struggle.
 *
 * Navigation and Search: Having a topics table can facilitate easier navigation and searching for questions based on users' preferred topics.
 *
 * Educational Planning: In an educational context, topics help instructors plan lessons and assessments more effectively by aligning them with specific areas of learning.
 *
 * Overall, a topics table adds a layer of organization and structure to your quiz system that can greatly enhance the user experience, content management, and analytics capabilities. It allows you to create a more tailored learning and assessment experience for your users
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * */