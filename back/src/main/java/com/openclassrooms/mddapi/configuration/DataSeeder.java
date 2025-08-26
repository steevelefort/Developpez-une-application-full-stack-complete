package com.openclassrooms.mddapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.service.ThemeService;

@Component
public class DataSeeder implements CommandLineRunner {

  @Autowired
  private ThemeService themeService;

  @Override
  public void run(String... args) throws Exception {
    themeService.seedData();
  }
    
}
