package com.jerry.config;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections; // Import for Collections.emptyList()

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
        
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7); // Remove "Bearer " prefix
            
            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
                
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt) // Parse the JWT token
                        .getBody();
                
                String email = claims.get("email", String.class); // Safely retrieve the email claim
                
                // Create an Authentication object with the extracted email
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        email, 
                        null, 
                        Collections.emptyList() // Provide authorities if necessary
                );
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } 
            catch (Exception e) {
                // Log the exception (optional, but recommended for debugging)
                System.err.println("Token validation failed: " + e.getMessage());
                throw new BadCredentialsException("Invalid token...");
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
