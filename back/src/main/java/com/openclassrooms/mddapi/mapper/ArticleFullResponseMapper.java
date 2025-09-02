package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.response.ArticleFullResponse;
import com.openclassrooms.mddapi.model.Article;

/**
 * Maps Article entities with full details
 */
@Mapper(componentModel = "spring", uses = {CommentResponseMapper.class})
public interface ArticleFullResponseMapper {

  /**
   * Converts Article entity to ArticleFullResponse
   *
   * @param article the article entity
   * @return full article response
   */
  @Mapping(source = "user.userName", target = "userName")
  @Mapping(source = "theme.name", target = "themeName")
  ArticleFullResponse toResponse(Article article);

  /**
   * Converts ArticleFullResponse to Article entity
   *
   * @param articleResponse the article response
   * @return article entity
   */
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "theme", ignore = true)
  @Mapping(target = "comments", ignore = true)
  Article toEntity(ArticleFullResponse articleResponse);

  /**
   * Converts list of Article entities to list of ArticleFullResponse
   *
   * @param articles the list of article entities
   * @return list of full article responses
   */
  List<ArticleFullResponse> toResponseList(List<Article> articles);

}
