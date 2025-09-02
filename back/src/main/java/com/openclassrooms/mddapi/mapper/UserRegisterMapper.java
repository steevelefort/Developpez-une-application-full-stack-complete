package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.request.UserRegisterRequest;
import com.openclassrooms.mddapi.model.User;

/**
 * Maps User registration requests
 */
@Mapper(componentModel = "spring")
public interface UserRegisterMapper {
  /**
   * Converts UserRegisterRequest to User entity
   *
   * @param userRegisterRequest the registration request
   * @return user entity
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "themes", ignore = true)
  User toEntity(UserRegisterRequest userRegisterRequest);
}
