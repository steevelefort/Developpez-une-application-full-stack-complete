package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.response.ThemeResponse;
import com.openclassrooms.mddapi.model.Theme;

/**
 * Maps Theme entities and responses
 */
@Mapper(componentModel = "spring")
public interface ThemeMapper {

  /**
   * Converts Theme entity to ThemeResponse
   *
   * @param theme the theme entity
   * @return theme response
   */
  ThemeResponse toResponse(Theme theme);

  /**
   * Converts ThemeResponse to Theme entity
   *
   * @param themeDto the theme response
   * @return theme entity
   */
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "users", ignore = true)
  Theme toEntity(ThemeResponse themeDto);

  /**
   * Converts list of Theme entities to list of ThemeResponse
   *
   * @param themes the list of theme entities
   * @return list of theme responses
   */
  List<ThemeResponse> toResponseList(List<Theme> themes);

}
