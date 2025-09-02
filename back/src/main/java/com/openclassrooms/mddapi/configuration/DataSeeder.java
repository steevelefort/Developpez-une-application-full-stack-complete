package com.openclassrooms.mddapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.service.ThemeService;

/**
 * Loads initial data when the application starts
 */
@Component
public class DataSeeder implements CommandLineRunner {

  @Autowired
  private ThemeService themeService;

  /**
   * Runs when the application starts to load initial data
   *
   * @param args command line arguments
   * @throws Exception if data loading fails
   */
  @Override
  public void run(String... args) throws Exception {
    themeService.seedData();
  }
    
}
