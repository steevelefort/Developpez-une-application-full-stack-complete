package com.openclassrooms.mddapi.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.response.ThemeResponse;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Theme service.
 */
@Service
public class ThemeService {

  @Autowired
  ThemeRepository themeRepository;

  @Autowired
  ThemeMapper themeMapper;

  /**
   * Find all themes.
   * @return list of themes
   */
  public List<ThemeResponse> findAll() {
    return themeMapper.toResponseList(themeRepository.findAll());
  }

  /**
   * Find theme by id.
   * @param id theme id
   * @return theme data
   */
  public ThemeResponse findById(Long id) {
    Theme theme = themeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    return themeMapper.toResponse(theme);
  }

  /**
   * Seed themes data.
   */
  public void seedData() {
    if (themeRepository.count() == 0) {
      List<Theme> themes = Arrays.asList(
          new Theme("JavaScript", "Langage de programmation pour le web côté client et serveur"),
          new Theme("Java", "Langage orienté objet pour applications d'entreprise et Android"),
          new Theme("Python", "Langage polyvalent pour développement web, IA et data science"),
          new Theme("Angular", "Framework TypeScript pour applications web single-page"),
          new Theme("React", "Bibliothèque JavaScript pour construire des interfaces utilisateur"),
          new Theme("Spring Boot", "Framework Java pour développement d'API REST et microservices"),
          new Theme("Node.js", "Runtime JavaScript côté serveur pour applications backend"),
          new Theme("DevOps", "Pratiques et outils pour automatisation et déploiement continu"),
          new Theme("Base de données", "Conception, optimisation et gestion des systèmes de données"),
          new Theme("Sécurité", "Bonnes pratiques et techniques de cybersécurité applicative")
          );
      themeRepository.saveAll(themes);
    }
  }

}
