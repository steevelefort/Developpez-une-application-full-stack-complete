package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Theme;

/**
 * Repository for Theme data access
 */
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

}
