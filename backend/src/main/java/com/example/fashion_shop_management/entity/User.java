package com.example.fashion_shop_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
<<<<<<< HEAD

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
=======
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
<<<<<<< HEAD
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
=======
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username", nullable = false, unique = true)
    String username;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "gender")
    String gender;

    @Column(name = "phone")
    String phone;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "address")
    String address;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "enabled")
    boolean enabled;

    @Column(name = "account_locked")
    boolean accountLocked;

    @Column(name = "verification_code")
    String verificationCode;

    @Column(name = "verification_expired")
    LocalDateTime verificationCodeExpired;

    @Column(name = "access_token")
    String accessToken;

    @Column(name = "refresh_token")
    String refreshToken;

    @CreatedDate
    @Column(name = "create_date", nullable = false)
    LocalDateTime createDate;
    @LastModifiedDate
    @Column(name = "update_date", insertable = false)
    LocalDateTime updateDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Order> orders = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles == null) {
            return Collections.emptyList();
        }
        return this.roles.stream()
                .map(r -> r.getName() != null ? new SimpleGrantedAuthority(r.getName()) : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870
}
