package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.auth;

import java.security.Key;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenJwtConfig {

        public final static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        public final static String PREFIX_TOKEN = "Bearer ";
        public final static String HEADER_AUTHORIZATION = "Authorization";
//        public static final String CONTENT_TYPE = "application/json";

}
