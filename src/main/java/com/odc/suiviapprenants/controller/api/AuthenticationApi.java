package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.dto.auth.AuthenticationRequest;
import com.odc.suiviapprenants.dto.auth.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApi {

  @PostMapping("/login")
  ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

  @GetMapping("/promoEncours")
  PromoDto promoEncours();

}
