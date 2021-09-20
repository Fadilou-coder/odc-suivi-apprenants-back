package com.odc.suiviapprenants.controller;


import com.odc.suiviapprenants.controller.api.AuthenticationApi;
import com.odc.suiviapprenants.dto.auth.AuthenticationRequest;
import com.odc.suiviapprenants.dto.auth.AuthenticationResponse;
import com.odc.suiviapprenants.service.impl.UserDetailsServiceImpl;
import com.odc.suiviapprenants.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AuthenticationController implements AuthenticationApi {
  private AuthenticationManager authenticationManager;
  private UserDetailsServiceImpl userDetailsService;
  private JwtUtil jwtUtil;

  @Override
  public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

    final String jwt = jwtUtil.generateToken((User) userDetails);
    List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return ResponseEntity.ok(
            new AuthenticationResponse(jwt,roles)
    );
  }

}
