package com.odc.suiviapprenants.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    public JwtAuthFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        com.odc.suiviapprenants.entity.User appUser = null;
        try {
            appUser = new ObjectMapper().readValue(request.getInputStream(), com.odc.suiviapprenants.entity.User.class); // pour deserializer
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                appUser.getUsername(), appUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        return authentication;

    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User springUser=(User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET);
        String jwtToken = JWT.create()
                .withSubject(springUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 8*3600*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", springUser.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);

        Map<String, String> idToken = new HashMap<>();
        idToken.put("token", jwtToken);
        idToken.put("username", springUser.getUsername());

        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("token", jwtToken);
        jsonObject.appendField("username", springUser.getUsername());
        jsonObject.appendField("role", springUser.getAuthorities());

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), jsonObject);
    }


}
