package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.request.ArticleRequest;
import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.model.Article;

/**
 * Maps Article requests
 */
@Mapper(componentModel = "spring", uses = {UserResponseMapper.class, ThemeMapper.class})
public interface ArticleRequestMapper {


  /**
   * Converts ArticleRequest to Article entity
   *
   * @param articleRequest the article request
   * @return article entity
   */
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "theme", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "comments", ignore = true)
  Article toEntity(ArticleRequest articleRequest);

  /**
   * Converts Article entity to ArticleResponse
   *
   * @param article the article entity
   * @return article response
   */
  @Mapping(source = "user.userName", target = "userName")
  @Mapping(source = "theme.name", target = "themeName")
  ArticleResponse toResponse(Article article);

  /**
   * Converts list of Article entities to list of ArticleResponse
   *
   * @param articles the list of article entities
   * @return list of article responses
   */
  List<ArticleResponse> toResponseList(List<Article> articles);

}
