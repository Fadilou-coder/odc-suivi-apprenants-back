package com.odc.suiviapprenants.controller;


import com.odc.suiviapprenants.controller.api.AuthenticationApi;
import com.odc.suiviapprenants.dto.auth.AuthenticationRequest;
import com.odc.suiviapprenants.dto.auth.AuthenticationResponse;
import com.odc.suiviapprenants.service.impl.UserDetailsServiceImpl;
import com.odc.suiviapprenants.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthenticationController implements AuthenticationApi {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
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
