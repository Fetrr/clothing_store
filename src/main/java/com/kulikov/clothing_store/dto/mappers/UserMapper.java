package com.kulikov.clothing_store.dto.mappers;

import com.kulikov.clothing_store.dto.request.UserRequestDto;
import com.kulikov.clothing_store.dto.response.UserResponseDto;
import com.kulikov.clothing_store.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserRequestDto dto);
    List<User> toEntityList(List<UserRequestDto> dtoList);

    UserResponseDto toResponse(User user);
    List<UserResponseDto> toResponseList(List<User> userList);
}
