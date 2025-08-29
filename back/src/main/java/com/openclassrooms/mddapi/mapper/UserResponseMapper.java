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

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

  @Mapping(target = "password", ignore = true)
  @Mapping(target = "themes", ignore = true)
  User toEntity(UserResponse userResponseRequest);

  // @Mapping(target = "subscriptions", expression =
  // "java(user.getThemes().stream().map(theme ->
  // theme.getId()).collect(java.util.stream.Collectors.toList()))")
  // UserResponse toResponse(User user);

  @Mapping(target = "subscriptions", source = "themes", qualifiedByName = "getThemesIds")
  UserResponse toResponse(User user);

  @Named("getThemesIds")
  default List<Long> getThemesIds(Set<Theme> themes) {
    List<Long> themesIds = new ArrayList<>();
    for (Theme theme : themes) {
      themesIds.add(theme.getId());
    }
    return themesIds;
  }
}
