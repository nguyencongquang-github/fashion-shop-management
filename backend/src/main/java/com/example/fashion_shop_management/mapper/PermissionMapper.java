package com.example.fashion_shop_management.mapper;

import com.example.fashion_shop_management.dto.request.PermissionRequest;
import com.example.fashion_shop_management.dto.response.PermissionResponse;
import com.example.fashion_shop_management.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
