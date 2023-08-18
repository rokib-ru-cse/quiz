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
//@Table(name = "question_taggings")
//public class QuestionTagging {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private Question question;
//
//    @Column(nullable = false)
//    private String tag;
//
//    @Override
//    public String toString() {
//        return "QuestionTagging{" + "id=" + id + ", question=" + question + ", tag='" + tag + '\'' + '}';
//    }
//}
//
