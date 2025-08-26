package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.model.Article;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

  ArticleResponse toResponse(Article article);
  Article toEntity(ArticleResponse articleResponse);
  List<ArticleResponse> toResponseList(List<Article> articles);

}
