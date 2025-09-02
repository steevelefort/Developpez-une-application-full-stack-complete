package com.openclassrooms.mddapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.request.ArticleRequest;
import com.openclassrooms.mddapi.dto.request.CommentRequest;
import com.openclassrooms.mddapi.dto.request.UserUpdateRequest;
import com.openclassrooms.mddapi.dto.response.ArticleFullResponse;
import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.dto.response.BasicResponse;
import com.openclassrooms.mddapi.dto.response.CommentResponse;
import com.openclassrooms.mddapi.dto.response.UserResponse;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.CommentService;
import com.openclassrooms.mddapi.service.UserService;

import jakarta.validation.Valid;

/**
 * Article controller.
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

  @Autowired
  ArticleService articleService;

  @Autowired
  CommentService commentService;

  /**
   * Get user feed.
   * @param jwt user token
   * @return list of articles
   */
  @GetMapping(value = "/feed", produces = "application/json")
  List<ArticleResponse> feed(@AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    return articleService.getUserFeed(userId);
  }

  /**
   * Get one article.
   * @param articleId article id
   * @return article data
   */
  @GetMapping(value = "/{articleId}", produces = "application/json")
  ArticleFullResponse getOne(@PathVariable Long articleId) {
    return articleService.getOneById(articleId);
  }

  /**
   * Create new article.
   * @param request article data
   * @param jwt user token
   * @return created article
   */
  @PostMapping(value = "", produces = "application/json")
  ArticleResponse create(@Valid @RequestBody ArticleRequest request, @AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    ArticleResponse response = articleService.create(request, userId);
    return response;
  }

  /**
   * Add comment to article.
   * @param request comment data
   * @param articleId article id
   * @param jwt user token
   * @return created comment
   */
  @PostMapping(value = "/{articleId}/comment", produces = "application/json")
  CommentResponse addCommentToArticle(@Valid @RequestBody CommentRequest request,@PathVariable Long articleId, @AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    return commentService.create(request, articleId, userId);
  }

  
}
