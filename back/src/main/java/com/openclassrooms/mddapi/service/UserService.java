package com.openclassrooms.mddapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.mddapi.dto.request.UserLoginRequest;
import com.openclassrooms.mddapi.dto.request.UserRegisterRequest;
import com.openclassrooms.mddapi.dto.response.AuthResponse;
import com.openclassrooms.mddapi.mapper.AuthResponseMapper;
import com.openclassrooms.mddapi.mapper.UserRegisterMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserRegisterMapper userRegisterMapper;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  AuthResponseMapper authResponseMapper;

  @Autowired
  JwtService jwtService;

  public AuthResponse register(@Valid UserRegisterRequest request) {
    User user = userRegisterMapper.toEntity(request);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);
    String token = jwtService.generateToken(user.getEmail(), savedUser.getId());
    AuthResponse authResponse = authResponseMapper.toResponse(savedUser);
    authResponse.setToken(token);
    return authResponse;
  }

  public AuthResponse login(@Valid UserLoginRequest request) {
    User foundUser = userRepository.findByEmailOrUserName(request.getIdentifier(), request.getIdentifier()).orElseThrow(
        // () -> new BadRequest("Identifiant ou mot de passe incorrect")
        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiant ou mot de passe incorrect"));

    if (!passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiant ou mot de passe incorrect");
    }
    String token = jwtService.generateToken(foundUser.getEmail(), foundUser.getId());
    AuthResponse authResponse = authResponseMapper.toResponse(foundUser);
    authResponse.setToken(token);
    return authResponse;
  }

}
