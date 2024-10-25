package com.example.fashion_shop_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String username;

    String fullname;
    String email;
    String password;
    Date dob;

    @ManyToMany
    Set<Role> roles;

//    @JsonIgnore
//    @OneToOne(mappedBy = "user")
//    Customer customer;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    List<Order> listOrder;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    List<Cart> listCart;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    List<Review> listReview;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    Set<Role> roles = new HashSet<>();
}
