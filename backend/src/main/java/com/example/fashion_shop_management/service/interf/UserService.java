package com.example.fashion_shop_management.service.interf;

import com.example.fashion_shop_management.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserInfo(Integer userId);
    Boolean deleteUser(Integer userId);
    UserDto updateUser(Integer userId, UserDto userDto);
}
