package com.openclassrooms.mddapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.mddapi.dto.request.UserLoginRequest;
import com.openclassrooms.mddapi.dto.request.UserRegisterRequest;
import com.openclassrooms.mddapi.dto.request.UserUpdateRequest;
import com.openclassrooms.mddapi.dto.response.AuthResponse;
import com.openclassrooms.mddapi.dto.response.UserResponse;
import com.openclassrooms.mddapi.mapper.UserRegisterMapper;
import com.openclassrooms.mddapi.mapper.UserResponseMapper;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

import jakarta.validation.Valid;

/**
 * User service.
 */
@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  ThemeRepository themeRepository;

  @Autowired
  UserRegisterMapper userRegisterMapper;

  @Autowired
  PasswordEncoder passwordEncoder;


  @Autowired
  UserResponseMapper userResponseMapper;

  @Autowired
  JwtService jwtService;

  /**
   * Register new user.
   * @param request user data
   * @return auth response
   */
  public AuthResponse register(UserRegisterRequest request) {
    User user = userRegisterMapper.toEntity(request);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);
    String token = jwtService.generateToken(user.getEmail(), savedUser.getId());
    AuthResponse authResponse = new AuthResponse();
    authResponse.setToken(token);
    return authResponse;
  }

  /**
   * Login user.
   * @param request login data
   * @return auth response
   */
  public AuthResponse login(UserLoginRequest request) {
    User foundUser = userRepository.findByEmailOrUserName(request.getIdentifier(), request.getIdentifier()).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiant ou mot de passe incorrect"));

    if (!passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiant ou mot de passe incorrect");
    }
    String token = jwtService.generateToken(foundUser.getEmail(), foundUser.getId());
    AuthResponse authResponse = new AuthResponse();
    authResponse.setToken(token);
    return authResponse;
  }

  /**
   * Update user.
   * @param request user data
   * @param userId user id
   * @return updated user
   */
  public UserResponse update(UserUpdateRequest request, Long userId) {
    if (userRepository.existsByEmailAndIdNot(request.getEmail(), userId)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette adresse mail est déjà utilisée");
    }
    if (userRepository.existsByUserNameAndIdNot(request.getUserName(), userId)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ce nom d'utilisateur est déjà pris");
    }
    User user = userRepository.findById(userId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    if (!request.getEmail().equals(user.getEmail())) {
      user.setEmail(request.getEmail());
    }
    if (!request.getUserName().equals(user.getUserName())) {
      user.setUserName(request.getUserName());
    }
    if (request.getPassword() != null && request.getPassword().length() > 0) {
      user.setPassword(passwordEncoder.encode(request.getPassword()));
    }
    user = userRepository.save(user);
    return userResponseMapper.toResponse(user);
  }

  /**
   * Subscribe to theme.
   * @param userId user id
   * @param themeId theme id
   */
  public void subscribe(Long userId, Long themeId) {
    User user = userRepository.findById(userId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    Theme theme = themeRepository.findById(themeId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme introuvable"));
    if (!user.getThemes().contains(theme)) {
      user.getThemes().add(theme);
      userRepository.save(user);
    }
  }

  /**
   * Unsubscribe from theme.
   * @param userId user id
   * @param themeId theme id
   */
  public void unsubscribe(Long userId, Long themeId) {
    User user = userRepository.findById(userId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    Theme theme = themeRepository.findById(themeId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme introuvable"));
    if (user.getThemes().contains(theme)) {
      user.getThemes().remove(theme);
      userRepository.save(user);
    }
  }

  /**
   * Find user by id.
   * @param userId user id
   * @return user data
   */
  public UserResponse findById(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    return userResponseMapper.toResponse(user);
  }

}
