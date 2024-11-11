package com.example.fashion_shop_management.config.security;

import com.example.fashion_shop_management.constant.PredefinedRole;
import com.example.fashion_shop_management.entity.Role;
import com.example.fashion_shop_management.entity.User;
import com.example.fashion_shop_management.repository.RoleRepository;
import com.example.fashion_shop_management.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_EMAIL = "admin@gmail.com";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @NonFinal
    static final String ADMIN_USERNAME = "admin";

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        log.info("Initializing application....");
        return args -> {
            if (userRepository.findByEmail(ADMIN_EMAIL).isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(PredefinedRole.CUSTOMER_ROLE)
                        .build());

                roleRepository.save(Role.builder()
                        .name(PredefinedRole.EMPLOYEE_ROLE)
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(PredefinedRole.ADMIN_ROLE)
                        .build());

                HashSet<Role> roles = new HashSet<>();
                roleRepository.findByName(PredefinedRole.ADMIN_ROLE).ifPresent(roles::add);
                System.out.println(roles);

                User user = User.builder()
                        .username(ADMIN_USERNAME)
                        .email(ADMIN_EMAIL)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .enabled(true)
                        .gender("Nam")
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialized successfully");
        };
    }
}
