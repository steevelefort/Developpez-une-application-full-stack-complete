package com.openclassrooms.mddapi.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.mddapi.dto.request.CommentRequest;
import com.openclassrooms.mddapi.dto.response.CommentResponse;
import com.openclassrooms.mddapi.mapper.CommentRequestMapper;
import com.openclassrooms.mddapi.mapper.CommentResponseMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Comment service.
 */
@Service
public class CommentService {

  @Autowired
  CommentRepository commentRepository;

  @Autowired
  ArticleRepository articleRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CommentResponseMapper commentResponseMapper;

  @Autowired
  CommentRequestMapper commentRequestMapper;

  /**
   * Create new comment.
   * @param request comment data
   * @param articleId article id
   * @param userId user id
   * @return created comment
   */
  public CommentResponse create(CommentRequest request, Long articleId, Long userId){
    Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article introuvable"));
    User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));

    Comment comment = commentRequestMapper.toEntity(request);
    comment.setUser(user);
    comment.setArticle(article);
    Comment savedComment = commentRepository.save(comment);
    return commentResponseMapper.toResponse(savedComment);
  }

}
