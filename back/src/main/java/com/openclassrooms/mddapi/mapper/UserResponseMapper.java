package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.response.UserResponse;
import com.openclassrooms.mddapi.model.User;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

  @Mapping(target = "password", ignore = true)
  User toEntity(UserResponse userResponseRequest);
  UserResponse toResponse(User user);
}
