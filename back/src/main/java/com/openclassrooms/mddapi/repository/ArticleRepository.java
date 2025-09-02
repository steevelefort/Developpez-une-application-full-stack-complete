package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Article;

/**
 * Repository for Article data access
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

  /**
   * Finds articles for a user's feed based on their theme subscriptions
   *
   * @param userId the ID of the user
   * @return list of articles from themes the user follows
   */
  @Query("SELECT DISTINCT a FROM Article a " +
      "JOIN FETCH a.theme t " +
      "JOIN FETCH a.user u " +
      "WHERE t IN (SELECT th FROM User usr JOIN usr.themes th WHERE usr.id = :userId) " +
      "ORDER BY a.createdAt DESC")
  List<Article> findUserFeedArticles(@Param("userId") Long userId);

}
