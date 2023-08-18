//package com.sigma.quiz.domain.entities;
//
//import com.bitspondon.quiz.domain.entities.User;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.Set;
//
//@Entity
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Table(name = "user_roles")
//public class UserRole {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false)
//    private String roleName;
//
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
//
//    @ElementCollection
//    private Set<String> permissions;
//
//    // Constructors, getters, setters, and other methods
//
//    @Override
//    public String toString() {
//        return "UserRole{" +
//                "id=" + id +
//                ", roleName='" + roleName + '\'' +
//                '}';
//    }
//}

/**
 * Explanation:
 * <p>
 * The UserRole class includes an @ElementCollection field named permissions to store permissions as a collection of strings.
 * The permissions field is used to store permission names associated with the role.
 * This approach simplifies the many-to-many relationship handling and is suitable when dealing with a relatively small number of permissions associated with each role.
 * <p>
 * Remember to adjust the class according to your application's requirements, and consider adding appropriate methods for querying and managing user roles and permissions.
 */