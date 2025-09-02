package com.openclassrooms.mddapi.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.openclassrooms.mddapi.dto.response.UserResponse;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;

/**
 * Maps User entities and responses
 */
@Mapper(componentModel = "spring")
public interface UserResponseMapper {

  /**
   * Converts UserResponse to User entity
   *
   * @param userResponseRequest the user response
   * @return user entity
   */
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "themes", ignore = true)
  User toEntity(UserResponse userResponseRequest);

  // @Mapping(target = "subscriptions", expression =
  // "java(user.getThemes().stream().map(theme ->
  // theme.getId()).collect(java.util.stream.Collectors.toList()))")
  // UserResponse toResponse(User user);

  /**
   * Converts User entity to UserResponse
   *
   * @param user the user entity
   * @return user response
   */
  @Mapping(target = "subscriptions", source = "themes", qualifiedByName = "getThemesIds")
  UserResponse toResponse(User user);

  /**
   * Extracts theme IDs from a set of themes
   *
   * @param themes the set of themes
   * @return list of theme IDs
   */
  @Named("getThemesIds")
  default List<Long> getThemesIds(Set<Theme> themes) {
    List<Long> themesIds = new ArrayList<>();
    for (Theme theme : themes) {
      themesIds.add(theme.getId());
    }
    return themesIds;
  }
}
