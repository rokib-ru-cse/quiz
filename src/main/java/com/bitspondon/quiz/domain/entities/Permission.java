//package com.bitspondon.quiz.domain.entities;
//
//
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
//@Table(name = "permissions")
//public class Permission {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false)
//    private String permissionName;
//
//    @ManyToMany(mappedBy = "permissions")
//    private Set<UserRole> roles;
//
//    // Constructors, getters, setters, and other methods
//
//    @Override
//    public String toString() {
//        return "Permission{" +
//                "id=" + id +
//                ", permissionName='" + permissionName + '\'' +
//                '}';
//    }
//}
//
//
//
/**
 * Explanation:
 * <p>
 * The UserRole class represents roles assigned to users.
 * The Permission class represents specific permissions that can be associated with roles.
 * The roles field in the Permission class establishes a many-to-many relationship with UserRole to indicate which roles have the permission.
 * The permissions field in the UserRole class establishes a many-to-many relationship with Permission to indicate which permissions are associated with the role.
 * This approach allows you to define user roles and associated permissions, making it easier to manage access control and security in your application.
 * <p>
 * As always, customize the classes according to your application's requirements, and consider adding appropriate methods for querying and managing user roles and permissions.
 */