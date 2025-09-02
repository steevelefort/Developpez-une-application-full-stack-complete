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

import com.openclassrooms.mddapi.dto.response.ThemeResponse;
import com.openclassrooms.mddapi.service.ThemeService;

import jakarta.validation.Valid;

// @CrossOrigin(origins = "*", maxAge = 3600)
/**
 * Theme controller.
 */
@RestController
@RequestMapping("/api/theme")
public class ThemeController {

  @Autowired
  ThemeService themeService;

  /**
   * Get all themes.
   * @return list of themes
   */
  @GetMapping("")
  List<ThemeResponse> getAllThemes() {
    return themeService.findAll();
  }

  /**
   * Get theme by id.
   * @param id theme id
   * @return theme data
   */
  @GetMapping("/{id}")
  ThemeResponse getThemeById(@PathVariable Long id) {
    return themeService.findById(id);
  }

}
