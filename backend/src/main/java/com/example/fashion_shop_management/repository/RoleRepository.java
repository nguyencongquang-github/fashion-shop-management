package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface RoleRepository extends JpaRepository<Role, String> {
}
=======
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870
