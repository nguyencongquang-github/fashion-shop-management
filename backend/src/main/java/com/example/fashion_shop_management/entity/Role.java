package com.example.fashion_shop_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
<<<<<<< HEAD

import java.util.Set;

@Entity
@Table(name = "role")
=======
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "roles")
>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
<<<<<<< HEAD
public class Role {
    @Id
    String name;

    String description;

    @ManyToMany
    Set<Permission> permissions;
=======
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870
}
