package com.chatio.chatiobackend.jwt.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

  private final JwtEncoder encoder;
  private final JwtDecoder decoder;

  public String generateToken(Authentication authentication) {
    Instant now = Instant.now();
    String scope = "ROLE_ADMIN";
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(now)
        .expiresAt(now.plus(1, ChronoUnit.HOURS))
        .subject(authentication.getName())
        .claim("scope", scope)
        .build();
    var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
    return this.encoder.encode(encoderParameters).getTokenValue();
  }

  public Long extractExpirationTime(String token) {
    Jwt jwt = decoder.decode(token);
    var exp = (Instant) jwt.getClaim("exp");
    return exp.toEpochMilli();
  }
}
