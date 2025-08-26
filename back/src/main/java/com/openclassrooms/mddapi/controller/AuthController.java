package com.openclassrooms.mddapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.request.UserRegisterRequest;
import com.openclassrooms.mddapi.dto.response.AuthResponse;
import com.openclassrooms.mddapi.dto.response.BasicResponse;
import com.openclassrooms.mddapi.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  UserService userService;

  @PostMapping(value = "/register", produces = "application/json")
  // @SecurityRequirements({})
  public BasicResponse register(@Valid @RequestBody UserRegisterRequest request) {
    userService.register(request);
    return new BasicResponse("Utilisateur créé avec succés");
  }


  // @GetMapping("")
  // List<ThemeResponse> getAllThemes() {
  //   return themeService.findAll();
  // }
  //
  // @GetMapping("/{id}")
  // ThemeResponse getThemeById(@PathVariable Long id) {
  //   return themeService.findById(id);
  // }

}
