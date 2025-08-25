package com.openclassrooms.mddapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ThemeService {

  @Autowired
  ThemeRepository themeRepository;

  @Autowired
  ThemeMapper themeMapper;

  public List<ThemeDto> findAll() {
    return themeMapper.toDtoList(themeRepository.findAll());
  }

  public ThemeDto create(ThemeDto themeDto) {
    Theme theme = themeMapper.toEntity(themeDto);
    ThemeDto response = themeMapper.toDto(themeRepository.save(theme));
    return response;
  }

  public ThemeDto findById(Long id) {
    Theme theme = themeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    return themeMapper.toDto(theme);
  }

}
