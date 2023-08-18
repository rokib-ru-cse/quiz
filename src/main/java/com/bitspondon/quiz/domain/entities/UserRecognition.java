//package com.bitspondon.quiz.domain.entities;
//
//
//import jakarta.persistence.*;

//import com.bitspondon.quiz.domain.entities.User;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDate;
//
//@Entity
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Table(name = "user_recognition")
//public class UserRecognition {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column(nullable = false)
//    private String description;
//
//    @Column(nullable = false)
//    private boolean isCertificate; // Flag to differentiate between certificates and achievements
//
//    @Column
//    private LocalDate issuanceDate; // Use only for certificates
//
//    // Constructors, getters, setters, and other methods
//
//    @Override
//    public String toString() {
//        return "UserRecognition{" +
//                "id=" + id +
//                ", user=" + user +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", isCertificate=" + isCertificate +
//                ", issuanceDate=" + issuanceDate +
//                '}';
//    }
//}


/**
 * Explanation:
 * <p>
 * The UserRecognition class combines the functionality of both certificates and achievements into a single table.
 * The isCertificate field is a boolean flag that indicates whether the record represents a certificate (true) or an achievement (false).
 * The issuanceDate field is used only for certificates to store the date of issuance.
 * Use Case:
 * <p>
 * The UserRecognition table serves as a consolidated repository for tracking and managing various recognitions received by users, including both certificates and achievements. Here are some use cases:
 * <p>
 * Recognition Management: Store certificates and achievements earned by users in a single table, simplifying the tracking and management of their accomplishments.
 * <p>
 * Acknowledgment: Use the table to acknowledge and celebrate users' achievements and milestones, whether they are specific accomplishments or general recognition.
 * <p>
 * Profile Display: Display a user's recognitions on their profile page, showcasing their achievements and certificates in one place.
 * <p>
 * Reporting and Analytics: Generate reports and analyze data related to user recognitions, such as the distribution of certificates vs. achievements, the most common titles, and more.
 * <p>
 * Customization: Allow users to customize their profiles by adding, editing, or removing their recognitions.
 * <p>
 * Gamification: Integrate the recognitions into a gamification system, rewarding users for their accomplishments and encouraging engagement.
 * <p>
 * Incentives: Offer incentives or rewards to users based on their achievements and certificates.
 * <p>
 * By consolidating certificates and achievements into a single table, you simplify data management, improve reporting capabilities, and provide a more versatile platform for recognizing user accomplishments.
 */