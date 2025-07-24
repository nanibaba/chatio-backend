package com.chatio.chatiobackend.jwt.controller;

import com.chatio.chatiobackend.jwt.model.auth.AuthRequest;
import com.chatio.chatiobackend.jwt.model.auth.AuthResponse;
import com.chatio.chatiobackend.jwt.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/token")
  public AuthResponse login(@RequestBody AuthRequest authRequest) {
    return authService.authenticate(authRequest);
  }
}