package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;

import com.openclassrooms.mddapi.dto.request.UserRegisterRequest;
import com.openclassrooms.mddapi.model.User;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {
  User toEntity(UserRegisterRequest userRegisterRequest);
}
