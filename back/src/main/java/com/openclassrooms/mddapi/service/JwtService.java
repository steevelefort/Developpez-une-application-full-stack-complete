package com.openclassrooms.mddapi.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Autowired
  private JwtEncoder jwtEncoder;

  @Value("${app.jwt.validity}")
  private Integer jwtValidity;

  public JwtService() {
  }

  /**
   * Generates a JSON Web Token from an email and user id
   *
   * @param email an email address
   * @param id a user id
   * @return String a json web token
   */
  public String generateToken(String email, Long id) {
    Instant now = Instant.now();

    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuer("chatop-api")
      .issuedAt(now)
      .expiresAt(now.plus(jwtValidity, ChronoUnit.HOURS))
      .subject(email)
      .claim("userId", id)
      .build();
    
    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

}
