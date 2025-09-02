package com.openclassrooms.mddapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
 * User controller.
 */
// @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserService userService;

  /**
   * Get current user.
   * @param jwt user token
   * @return user data
   */
  @GetMapping(value = "/me", produces = "application/json")
  UserResponse getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    return userService.findById(userId);
  }

  /**
   * Update user.
   * @param request user data
   * @param jwt user token
   * @return updated user
   */
  @PutMapping(value = "/update", produces = "application/json")
  UserResponse update(@Valid @RequestBody UserUpdateRequest request, @AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    UserResponse response = userService.update(request, userId);
    return response;
  }

  /**
   * Subscribe to theme.
   * @param themeId theme id
   * @param jwt user token
   * @return success message
   */
  @PostMapping(value = "/subscribe/{themeId}", produces = "application/json")
  BasicResponse subscribe(@PathVariable Long themeId, @AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    userService.subscribe(userId, themeId);
    return new BasicResponse("Abonnement effectué");
  }

  /**
   * Unsubscribe from theme.
   * @param themeId theme id
   * @param jwt user token
   * @return success message
   */
  @DeleteMapping(value = "/subscribe/{themeId}", produces = "application/json")
  BasicResponse unSubscribe(@PathVariable Long themeId, @AuthenticationPrincipal Jwt jwt) {
    Long userId = ((Number) jwt.getClaim("userId")).longValue();
    userService.unsubscribe(userId, themeId);
    return new BasicResponse("Désabonnement effectué");
  }

}
