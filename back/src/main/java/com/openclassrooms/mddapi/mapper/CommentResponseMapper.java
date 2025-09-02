package com.openclassrooms.mddapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.response.CommentResponse;
import com.openclassrooms.mddapi.model.Comment;

/**
 * Maps Comment entities and responses
 */
@Mapper(componentModel = "spring")
public interface CommentResponseMapper {

  /**
   * Converts Comment entity to CommentResponse
   *
   * @param comment the comment entity
   * @return comment response
   */
  @Mapping(source = "user.userName", target = "userName")
  // @Mapping(source = "theme.name", target = "themeName")
  CommentResponse toResponse(Comment comment);
  //
  // @Mapping(target = "comments", ignore = true)
  // Article toEntity(ArticleResponse articleResponse);

  /**
   * Converts list of Comment entities to list of CommentResponse
   *
   * @param comments the list of comment entities
   * @return list of comment responses
   */
  List<CommentResponse> toResponseList(List<Comment> comments);

}
