package com.openclassrooms.mddapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.mddapi.dto.request.ArticleRequest;
import com.openclassrooms.mddapi.dto.response.ArticleFullResponse;
import com.openclassrooms.mddapi.dto.response.ArticleResponse;
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


}
