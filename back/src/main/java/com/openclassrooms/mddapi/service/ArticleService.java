package com.openclassrooms.mddapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.mddapi.dto.response.AuthResponse;
import com.openclassrooms.mddapi.dto.request.ArticleRequest;
import com.openclassrooms.mddapi.dto.response.ArticleFullResponse;
import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.mapper.AuthResponseMapper;
import com.openclassrooms.mddapi.mapper.ArticleFullResponseMapper;
import com.openclassrooms.mddapi.mapper.ArticleRequestMapper;
import com.openclassrooms.mddapi.mapper.ArticleResponseMapper;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.repository.ArticleRepository;

import jakarta.validation.Valid;

@Service
public class ArticleService {

  @Autowired
  ArticleRepository articleRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ThemeRepository themeRepository;

  @Autowired
  ArticleRequestMapper articleRequestMapper;

  @Autowired
  ArticleResponseMapper articleResponseMapper;

  @Autowired
  ArticleFullResponseMapper articleFullResponseMapper;

  public ArticleResponse create(ArticleRequest request, Long userId) {
    Article article = articleRequestMapper.toEntity(request);
    User user = userRepository.findById(userId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    Theme theme = themeRepository.findById(request.getThemeId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme introuvable"));
    article.setUser(user);
    article.setTheme(theme);
    Article savedArticle = articleRepository.save(article);
    return articleResponseMapper.toResponse(savedArticle);
  }

  public ArticleFullResponse getOneById(Long id) {
    Article article = articleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article introuvable"));
    return articleFullResponseMapper.toResponse(article);
  }

  public List<ArticleResponse> getUserFeed(Long userId) {
    List<Article> articles = articleRepository.findUserFeedArticles(userId); 
    return articleResponseMapper.toResponseList(articles);
  }

  // public AuthResponse register(ArticleRegisterRequest request) {
  //   Article user = userRegisterMapper.toEntity(request);
  //   user.setPassword(passwordEncoder.encode(user.getPassword()));
  //   Article savedArticle = userRepository.save(user);
  //   String token = jwtService.generateToken(user.getEmail(), savedArticle.getId());
  //   AuthResponse authResponse = authResponseMapper.toResponse(savedArticle);
  //   authResponse.setToken(token);
  //   return authResponse;
  // }
  //
  // public AuthResponse login(ArticleLoginRequest request) {
  //   Article foundArticle = userRepository.findByEmailOrArticleName(request.getIdentifier(), request.getIdentifier()).orElseThrow(
  //       // () -> new BadRequest("Identifiant ou mot de passe incorrect")
  //       () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiant ou mot de passe incorrect"));
  //
  //   if (!passwordEncoder.matches(request.getPassword(), foundArticle.getPassword())) {
  //     throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiant ou mot de passe incorrect");
  //   }
  //   String token = jwtService.generateToken(foundArticle.getEmail(), foundArticle.getId());
  //   AuthResponse authResponse = authResponseMapper.toResponse(foundArticle);
  //   authResponse.setToken(token);
  //   return authResponse;
  // }
  //
  // public ArticleResponse update(ArticleUpdateRequest request, Long userId) {
  //   if (userRepository.existsByEmailAndIdNot(request.getEmail(), userId)) {
  //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette adresse mail est déjà utilisée");
  //   }
  //   if (userRepository.existsByArticleNameAndIdNot(request.getArticleName(), userId)) {
  //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ce nom d'utilisateur est déjà pris");
  //   }
  //   Article user = userRepository.findById(userId).orElseThrow(
  //       () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
  //   if (!request.getEmail().equals(user.getEmail())) {
  //     user.setEmail(request.getEmail());
  //   }
  //   if (!request.getArticleName().equals(user.getArticleName())) {
  //     user.setArticleName(request.getArticleName());
  //   }
  //   if (request.getPassword() != null && request.getPassword().length() > 0) {
  //     user.setPassword(passwordEncoder.encode(request.getPassword()));
  //   }
  //   user = userRepository.save(user);
  //   return userResponseMapper.toResponse(user);
  // }
  //
  // public void subscribe(Long userId, Long themeId) {
  //   Article user = userRepository.findById(userId).orElseThrow(
  //       () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
  //   Theme theme = themeRepository.findById(themeId).orElseThrow(
  //       () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme introuvable"));
  //   if (!user.getThemes().contains(theme)) {
  //     user.getThemes().add(theme);
  //     userRepository.save(user);
  //   }
  // }
  //
  // public void unsubscribe(Long userId, Long themeId) {
  //   Article user = userRepository.findById(userId).orElseThrow(
  //       () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
  //   Theme theme = themeRepository.findById(themeId).orElseThrow(
  //       () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme introuvable"));
  //   if (user.getThemes().contains(theme)) {
  //     user.getThemes().remove(theme);
  //     userRepository.save(user);
  //   }
  // }

}
