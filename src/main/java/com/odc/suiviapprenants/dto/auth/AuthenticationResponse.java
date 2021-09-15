package com.odc.suiviapprenants.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

  private String accessToken;
  private List<String> role;

//  public AuthenticationResponse(String accessToken, List<String> roles)
//  {
//    this.accessToken = accessToken;
//    this.role = roles;
//  }

}
