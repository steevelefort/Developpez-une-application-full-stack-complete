package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.request.CommentRequest;
import com.openclassrooms.mddapi.dto.response.CommentResponse;
import com.openclassrooms.mddapi.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentRequestMapper {

  // @Mapping(source = "user.userName", target = "userName")
  // @Mapping(source = "theme.name", target = "themeName")
  // ArticleResponse toResponse(Article article);
  //
  // @Mapping(target = "comments", ignore = true)
  Comment toEntity(CommentRequest commentRequest);

  // List<CommentResponse> toResponseList(List<Comment> comments);

}
