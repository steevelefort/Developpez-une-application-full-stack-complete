package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.model.Article;

@Mapper(componentModel = "spring")
public interface ArticleResponseMapper {

  ArticleResponse toResponse(Article article);

  @Mapping(target = "comments", ignore = true)
  Article toEntity(ArticleResponse articleResponse);

  List<ArticleResponse> toResponseList(List<Article> articles);

}
