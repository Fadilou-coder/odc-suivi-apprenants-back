package com.odc.suiviapprenants.security;

public class SecurityConstants {
    public static final String SECRET = "maySecret1234";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 jours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
