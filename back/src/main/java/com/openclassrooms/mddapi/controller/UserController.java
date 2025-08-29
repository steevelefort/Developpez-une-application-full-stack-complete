package com.openclassrooms.mddapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.request.UserUpdateRequest;
import com.openclassrooms.mddapi.dto.response.BasicResponse;
import com.openclassrooms.mddapi.dto.response.UserResponse;
import com.openclassrooms.mddapi.service.UserService;

import jakarta.validation.Valid;

/**
 * REST controller for user management operations.
 * Handles user profile, subscriptions and authentication-related endpoints.
 */
// @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserService userService;

  /**
   * Updates the current user's profile information.
   * 
   * @param request the user update request containing new profile data
   * @param jwt the JWT token containing user authentication details  
   * @return UserResponse with updated user data
   */
  @PutMapping(value = "/update", produces = "application/json")
  UserResponse update(@Valid @RequestBody UserUpdateRequest request, @AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    UserResponse response = userService.update(request, userId);
    return response;
  }

  /**
   * Subscribes the current user to a specific theme.
   * 
   * @param themeId the ID of the theme to subscribe to
   * @param jwt the JWT token containing user authentication details
   * @return BasicResponse confirming the subscription
   */
  @PostMapping(value = "/subscribe/{themeId}", produces = "application/json")
  BasicResponse subscribe(@PathVariable Long themeId, @AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    userService.subscribe(userId, themeId);
    return new BasicResponse("Abonnement effectué");
  }

  /**
   * Unsubscribes the current user from a specific theme.
   * 
   * @param themeId the ID of the theme to unsubscribe from
   * @param jwt the JWT token containing user authentication details
   * @return BasicResponse confirming the unsubscription
   */
  @DeleteMapping(value = "/subscribe/{themeId}", produces = "application/json")
  BasicResponse unSubscribe(@PathVariable Long themeId, @AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    userService.unsubscribe(userId, themeId);
    return new BasicResponse("Désabonnement effectué");
  }

}
