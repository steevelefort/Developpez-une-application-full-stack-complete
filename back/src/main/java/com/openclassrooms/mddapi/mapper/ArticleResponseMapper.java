package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.model.Article;

/**
 * Maps Article entities and responses
 */
@Mapper(componentModel = "spring")
public interface ArticleResponseMapper {

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
   * Converts ArticleResponse to Article entity
   *
   * @param articleResponse the article response
   * @return article entity
   */
  @Mapping(target = "theme", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "comments", ignore = true)
  Article toEntity(ArticleResponse articleResponse);

  /**
   * Converts list of Article entities to list of ArticleResponse
   *
   * @param articles the list of article entities
   * @return list of article responses
   */
  List<ArticleResponse> toResponseList(List<Article> articles);

}
