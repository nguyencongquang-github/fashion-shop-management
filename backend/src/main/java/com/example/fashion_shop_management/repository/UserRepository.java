package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
//    @NonNull
//    Optional<User> findById(@NonNull Integer id);
    boolean existsByUsername(String username);
    List<User> findByUsernameContainingOrEmailContaining(String username, String password);


//    List<User> findByRole(String customer);
}
