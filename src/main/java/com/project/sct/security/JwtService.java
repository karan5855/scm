package com.project.sct.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

  @Value("${jwt.secret}") private String secret;
  @Value("${jwt.expiration-ms}") private long expiry;

  public String generateToken(UserDetails user) {
    return Jwts.builder()
            .setSubject(user.getUsername())  // equals email
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiry))
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
            .compact();
  }

  public String extractEmail(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
  }
//
//  private String createToken(Map<String,Object> claims, String subject) {
//    var now = new Date();
//    var expiry = new Date(now.getTime() + expirationMs);
//    return Jwts.builder()
//      .setClaims(claims)
//      .setSubject(subject)
//      .setIssuedAt(now)
//      .setExpiration(expiry)
//      .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
//      .compact();
//  }
//
//  // validate token
//  public boolean validateToken(String token, UserDetails userDetails) {
//    final String username = extractUsername(token);
//    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//  }
//
//  public String extractUsername(String token) {
//    return Jwts.parserBuilder()
//           .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
//           .build()
//           .parseClaimsJws(token).getBody().getSubject();
//  }
//
//  private boolean isTokenExpired(String token) {
//    Date expiration = Jwts.parserBuilder()
//      .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
//      .build()
//      .parseClaimsJws(token)
//      .getBody().getExpiration();
//    return expiration.before(new Date());
//  }
}
