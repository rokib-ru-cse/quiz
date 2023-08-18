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
//@Table(name = "notifications")
//public class Notification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User recipient;
//
//    @Column(nullable = false)
//    private String message;
//
//    @Column(nullable = false)
//    private boolean isRead;
//
//    @CreationTimestamp
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime timestamp;
//
//    // Constructors, getters, setters, and other methods
//
//    @Override
//    public String toString() {
//        return "Notification{" +
//                "id=" + id +
//                ", recipient=" + recipient +
//                ", message='" + message + '\'' +
//                ", isRead=" + isRead +
//                ", timestamp=" + timestamp +
//                '}';
//    }
//}


/**
 * Explanation:
 * <p>
 * The Notification class represents notifications sent to users.
 * It has a many-to-one relationship with the User entity, representing the user who receives the notification.
 * The message field stores the content of the notification.
 * The isRead field indicates whether the user has read the notification.
 * The timestamp field is automatically populated with the creation timestamp of the notification.
 * This class allows you to manage notifications within your application, ensuring that users receive important messages and enabling them to track which notifications they have read.
 * <p>
 * As always, customize the class according to your application's requirements, and consider adding appropriate methods for querying and managing notifications.
 */