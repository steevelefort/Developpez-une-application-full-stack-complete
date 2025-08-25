package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.model.Theme;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

  ThemeDto toDto(Theme theme);
  Theme toEntity(ThemeDto themeDto);
  List<ThemeDto> toDtoList(List<Theme> themes);

}
