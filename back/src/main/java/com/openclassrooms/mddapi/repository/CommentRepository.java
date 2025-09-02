package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Comment;

/**
 * Repository for Comment data access
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  
  /**
   * Finds all comments for a specific article
   *
   * @param articleId the ID of the article
   * @return list of comments for the article
   */
  List<Comment> findByArticleId(Long articleId);

}
