package com.openclassrooms.mddapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.mddapi.dto.request.UserRegisterRequest;
import com.openclassrooms.mddapi.mapper.UserRegisterMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserRegisterMapper userRegisterMapper;

  @Autowired
  PasswordEncoder passwordEncoder;

  public void register(@Valid UserRegisterRequest request) {
    User user = userRegisterMapper.toEntity(request);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);
  }

  // @Autowired
  // UserMapper userMapper;

  // public List<UserResponse> findAll() {
  // return userMapper.toResponseList(userRepository.findAll());
  // }
  //
  // public UserResponse findById(Long id) {
  // User user = userRepository.findById(id).orElseThrow(() -> new
  // EntityNotFoundException());
  // return userMapper.toResponse(user);
  // }

  // /**
  // * Seed some Themes in the database if empty
  // * Automatically runs at start by configuration/DataSeeder.java
  // */
  // public void seedData() {
  // if (themeRepository.count() == 0) {
  // List<Theme> themes = Arrays.asList(
  // new Theme("JavaScript", "Langage de programmation pour le web côté client et
  // serveur"),
  // new Theme("Java", "Langage orienté objet pour applications d'entreprise et
  // Android"),
  // new Theme("Python", "Langage polyvalent pour développement web, IA et data
  // science"),
  // new Theme("Angular", "Framework TypeScript pour applications web
  // single-page"),
  // new Theme("React", "Bibliothèque JavaScript pour construire des interfaces
  // utilisateur"),
  // new Theme("Spring Boot", "Framework Java pour développement d'API REST et
  // microservices"),
  // new Theme("Node.js", "Runtime JavaScript côté serveur pour applications
  // backend"),
  // new Theme("DevOps", "Pratiques et outils pour automatisation et déploiement
  // continu"),
  // new Theme("Base de données", "Conception, optimisation et gestion des
  // systèmes de données"),
  // new Theme("Sécurité", "Bonnes pratiques et techniques de cybersécurité
  // applicative")
  // );
  // themeRepository.saveAll(themes);
  // }
  // }

}
