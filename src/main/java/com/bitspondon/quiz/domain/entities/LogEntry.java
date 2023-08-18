//package com.bitspondon.quiz.domain.entities;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Table(name = "log_entries")
//public class LogEntry {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Column(nullable = false)
//    private String action;
//
//    @CreationTimestamp
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime timestamp;
//
//    // Constructors, getters, setters, and other methods
//
//    @Override
//    public String toString() {
//        return "LogEntry{" +
//                "id=" + id +
//                ", user=" + user +
//                ", action='" + action + '\'' +
//                ", timestamp=" + timestamp +
//                '}';
//    }
//}
//

/**
 * Explanation:
 *
 * The LogEntry class represents log entries that capture user actions and events in the system.
 * It has a many-to-one relationship with the User entity, representing the user who performed the action (optional, depending on your use case).
 * The action field stores a description of the action or event that occurred.
 * The timestamp field is automatically populated with the creation timestamp of the log entry.
 * This class allows you to maintain a record of important user actions, system events, or other activities for auditing, troubleshooting, and monitoring purposes.
 *
 * As always, customize the class according to your application's requirements, and consider adding appropriate methods for querying and managing log entries.
 */