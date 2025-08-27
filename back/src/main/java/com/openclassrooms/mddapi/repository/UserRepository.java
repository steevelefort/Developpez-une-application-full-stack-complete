package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);

  boolean existsByUserName(String userName);

  boolean existsByEmailOrUserName(String email, String userName);

  boolean existsByEmailAndIdNot(String email, Long id);

  boolean existsByUserNameAndIdNot(String userName, Long id);

  Optional<User> findByEmailOrUserName(String email, String userName);

}
