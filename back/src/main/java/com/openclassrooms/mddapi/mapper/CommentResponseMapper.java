package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.response.CommentResponse;
import com.openclassrooms.mddapi.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentResponseMapper {

  @Mapping(source = "user.userName", target = "userName")
  // @Mapping(source = "theme.name", target = "themeName")
  CommentResponse toResponse(Comment comment);
  //
  // @Mapping(target = "comments", ignore = true)
  // Article toEntity(ArticleResponse articleResponse);

  List<CommentResponse> toResponseList(List<Comment> comments);

}
