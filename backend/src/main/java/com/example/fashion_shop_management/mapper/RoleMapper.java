package com.example.fashion_shop_management.mapper;

import com.example.fashion_shop_management.dto.request.RoleRequest;
import com.example.fashion_shop_management.dto.response.RoleResponse;
import com.example.fashion_shop_management.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
