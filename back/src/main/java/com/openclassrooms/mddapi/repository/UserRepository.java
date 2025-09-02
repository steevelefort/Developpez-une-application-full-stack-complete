package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.User;

/**
 * Repository for User data access
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Checks if a user with this email already exists
   *
   * @param email the email to check
   * @return true if email exists, false otherwise
   */
  boolean existsByEmail(String email);

  /**
   * Checks if a user with this username already exists
   *
   * @param userName the username to check
   * @return true if username exists, false otherwise
   */
  boolean existsByUserName(String userName);

  /**
   * Checks if a user with this email or username already exists
   *
   * @param email the email to check
   * @param userName the username to check
   * @return true if email or username exists, false otherwise
   */
  boolean existsByEmailOrUserName(String email, String userName);

  /**
   * Checks if email exists for a different user (not this ID)
   *
   * @param email the email to check
   * @param id the user ID to exclude
   * @return true if email exists for another user, false otherwise
   */
  boolean existsByEmailAndIdNot(String email, Long id);

  /**
   * Checks if username exists for a different user (not this ID)
   *
   * @param userName the username to check
   * @param id the user ID to exclude
   * @return true if username exists for another user, false otherwise
   */
  boolean existsByUserNameAndIdNot(String userName, Long id);

  /**
   * Finds a user by email or username
   *
   * @param email the email to search for
   * @param userName the username to search for
   * @return Optional containing the user if found, empty otherwise
   */
  Optional<User> findByEmailOrUserName(String email, String userName);

}
