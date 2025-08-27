package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.request.UserRegisterRequest;
import com.openclassrooms.mddapi.dto.response.AuthResponse;
import com.openclassrooms.mddapi.model.User;

@Mapper(componentModel = "spring")
public interface AuthResponseMapper {

  @Mapping(target = "token", ignore = true)
  AuthResponse toResponse(User user);

  // @Mapping(target = "password", ignore = true)
  // User toEntity(AuthResponse authResponse);

}
