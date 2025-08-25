package com.openclassrooms.mddapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.service.ThemeService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/theme")
public class ThemeController {

  @Autowired
  ThemeService themeService;

  @GetMapping("")
  List<ThemeDto> getAllThemes() {
    return themeService.findAll();
  }

  @GetMapping("/{id}")
  ThemeDto getThemeById(@PathVariable Long id) {
    return themeService.findById(id);
  }

  @PostMapping("")
  ThemeDto createTheme(@Valid @RequestBody ThemeDto themeDto) {
    return themeService.create(themeDto);
  }

}
