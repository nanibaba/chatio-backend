package com.chatio.chatiobackend.jwt.model.auth;

import com.chatio.chatiobackend.jwt.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthUser implements UserDetails {

  private final User user;

  @Override
  public String getUsername() { return user.getUsername(); }

  @Override
  public String getPassword() { return user.getPassword(); }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // Return a list of roles or authorities assigned to the user.
    return Collections.emptyList();
  }

  @Override
  public boolean isAccountNonExpired() { return true; }

  @Override
  public boolean isAccountNonLocked() { return true; }

  @Override
  public boolean isCredentialsNonExpired() { return true; }

  @Override
  public boolean isEnabled() { return true; }
}