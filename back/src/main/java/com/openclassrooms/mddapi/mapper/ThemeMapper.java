package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.openclassrooms.mddapi.dto.response.ThemeResponse;
import com.openclassrooms.mddapi.model.Theme;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

  ThemeResponse toResponse(Theme theme);
  Theme toEntity(ThemeResponse themeDto);
  List<ThemeResponse> toResponseList(List<Theme> themes);

}
