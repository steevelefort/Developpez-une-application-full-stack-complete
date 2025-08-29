package com.openclassrooms.mddapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.request.UserLoginRequest;
import com.openclassrooms.mddapi.dto.request.UserRegisterRequest;
import com.openclassrooms.mddapi.dto.response.AuthResponse;
import com.openclassrooms.mddapi.dto.response.BasicResponse;
import com.openclassrooms.mddapi.dto.response.UserResponse;
import com.openclassrooms.mddapi.service.UserService;

import jakarta.validation.Valid;

// @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  UserService userService;

  @PostMapping(value = "/register", produces = "application/json")
  // @SecurityRequirements({})
  public AuthResponse register(@Valid @RequestBody UserRegisterRequest request) {
    return userService.register(request);
  }

  @PostMapping(value = "/login", produces = "application/json")
  public AuthResponse login(@Valid @RequestBody UserLoginRequest request) {
    return userService.login(request);
  }


  /**
   * Gets the current authenticated user's profile information.
   * 
   * @param jwt the JWT token containing user authentication details
   * @return UserResponse with current user data
   */
  @GetMapping(value = "/me", produces = "application/json")
  UserResponse getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    return userService.findById(userId);
  }

}
