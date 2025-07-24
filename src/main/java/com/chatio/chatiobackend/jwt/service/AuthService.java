package com.chatio.chatiobackend.jwt.service;

import com.chatio.chatiobackend.jwt.model.auth.AuthRequest;
import com.chatio.chatiobackend.jwt.model.auth.AuthResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenService jwtTokenService;

  public AuthResponse authenticate(AuthRequest authRequest) {
    var token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
    Authentication authentication = authenticationManager.authenticate(token);

    String jwtToken = jwtTokenService.generateToken(authentication);
    Long expiresAt = jwtTokenService.extractExpirationTime(jwtToken);

    return new AuthResponse(jwtToken, authentication.getName(), expiresAt);
  }
}