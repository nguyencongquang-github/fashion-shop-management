package com.example.fashion_shop_management.mapper;

<<<<<<< HEAD
import com.example.fashion_shop_management.dto.request.UserCreationRequest;
import com.example.fashion_shop_management.dto.request.UserUpdateRequest;
import com.example.fashion_shop_management.dto.response.UserResponse;
import com.example.fashion_shop_management.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
=======
import com.example.fashion_shop_management.dto.user.UserDto;
import com.example.fashion_shop_management.dto.user.UserResponseDTO;
import com.example.fashion_shop_management.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
    UserResponseDTO toUserResponseDTO(User user);
>>>>>>> 4b0814f078d63e6905e47a49a2ee0d6726113870
}
