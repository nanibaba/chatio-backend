package com.chatio.chatiobackend.jwt.service;

import com.chatio.chatiobackend.jwt.model.auth.AuthUser;
import com.chatio.chatiobackend.jwt.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .map(AuthUser::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }
}
