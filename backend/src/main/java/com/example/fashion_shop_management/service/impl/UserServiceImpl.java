package com.example.fashion_shop_management.service.impl;

import com.example.fashion_shop_management.dto.user.UserDto;
import com.example.fashion_shop_management.entity.User;
import com.example.fashion_shop_management.mapper.UserMapper;
import com.example.fashion_shop_management.repository.UserRepository;
import com.example.fashion_shop_management.service.interf.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getUserInfo(Integer userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return userMapper.toDto(userRepository.findById(userId).get());
    }




    public Boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        userRepository.deleteById(userId);

        return !userRepository.existsById(userId);
    }

    @Override
    public UserDto updateUser(Integer userId, UserDto userDto) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userRepository.findById(userId).get();
        user.setUsername(userDto.getUsername());
        user.setAvatar(userDto.getAvatar());
        user.setPhone(userDto.getPhone());
        user.setGender(userDto.getGender());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setAddress(userDto.getAddress());

        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
