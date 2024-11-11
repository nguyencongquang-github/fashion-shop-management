package com.example.fashion_shop_management.repository;

import com.example.fashion_shop_management.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionRepository extends JpaRepository<Permission, String> {
}
