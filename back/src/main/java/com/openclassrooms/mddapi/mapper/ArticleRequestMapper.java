package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.request.ArticleRequest;
import com.openclassrooms.mddapi.dto.response.ArticleResponse;
import com.openclassrooms.mddapi.model.Article;

@Mapper(componentModel = "spring", uses = {UserResponseMapper.class, ThemeMapper.class})
public interface ArticleRequestMapper {

  ArticleRequest toRequest(Article article);

  @Mapping(target = "user", ignore = true)
  @Mapping(target = "theme", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "comments", ignore = true)
  Article toEntity(ArticleRequest articleRequest);

  @Mapping(source = "user.userName", target = "userName")
  ArticleResponse toResponse(Article article);

  List<ArticleResponse> toResponseList(List<Article> articles);

}
