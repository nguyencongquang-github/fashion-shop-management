package com.example.fashion_shop_management.mapper;

import com.example.fashion_shop_management.dto.user.UserDto;
import com.example.fashion_shop_management.dto.user.UserResponseDTO;
import com.example.fashion_shop_management.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
    UserResponseDTO toUserResponseDTO(User user);
}
