package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.response.ArticleFullResponse;
import com.openclassrooms.mddapi.model.Article;

@Mapper(componentModel = "spring", uses = {CommentResponseMapper.class})
public interface ArticleFullResponseMapper {

  @Mapping(source = "user.userName", target = "userName")
  @Mapping(source = "theme.name", target = "themeName")
  ArticleFullResponse toResponse(Article article);

  @Mapping(target = "user", ignore = true)
  @Mapping(target = "theme", ignore = true)
  @Mapping(target = "comments", ignore = true)
  Article toEntity(ArticleFullResponse articleResponse);

  List<ArticleFullResponse> toResponseList(List<Article> articles);

}
