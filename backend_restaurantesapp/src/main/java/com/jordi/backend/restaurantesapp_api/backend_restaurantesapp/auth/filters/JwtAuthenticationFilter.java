package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.auth.filters;

import static com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.auth.TokenJwtConfig.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        User user = null;
        String username = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            username = user.getUsername();
            password = user.getPassword();

            // logger.info("Username desde request InputStream (raw) " + username);
            // logger.info("Password desde request InputStream (raw) " + password);

        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal())
                .getUsername();

        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        boolean isAdmin = roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        Claims claims = Jwts.claims();
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        claims.put("isAdmin", isAdmin);
        claims.put("username", username);

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("message", String.format("Hola %s, has iniciado sesion con exito!", username));
        body.put("username", username);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Error en la autenticacion username o password incorrecto!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//
//        User user = null;
//        String username = null;
//        String password = null;
//
//        try {
//            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
//            username = user.getUsername();
//            password = user.getPassword();
////        } catch (StreamReadException e) {
////            e.printStackTrace();
////        } catch (DatabindException e) {
////            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
//                password);
//
//        return authenticationManager.authenticate(authenticationToken);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//            Authentication authResult) throws IOException, ServletException {
//
//        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
//        String username = user.getUsername();
//        // Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
//        Collection<? extends GrantedAuthority> roles = user.getAuthorities();
//
//        Claims claims = Jwts.claims();
//        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
//        claims.put("username", username);
//
//
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
//                .setIssuedAt(new Date())
//                .signWith(SECRET_KEY)
//                .compact();
//
//        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
//
//        Map<String, String> body = new HashMap<>();
//        body.put("token", token);
//        body.put("username", username);
//        body.put("message", String.format("Hola %s has iniciado sesion con exito!", username));
//
//        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
//        response.setContentType(CONTENT_TYPE);
//        response.setStatus(200);
//
//    //     Map<String, Object> body = new HashMap<>();
//    //     body.put("token", token);
//    //     body.put("message", String.format("Hola %s, has iniciado sesion con exito!", username));
//    //     body.put("username", username);
//    //     response.getWriter().write(new ObjectMapper().writeValueAsString(body));
//    //     response.setStatus(200);
//    //     response.setContentType("application/json");
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//            AuthenticationException failed) throws IOException, ServletException {
//        Map<String, String> body = new HashMap<>();
//        body.put("message", "Error en la autenticacion username o password incorrectos!");
//        body.put("error", failed.getMessage());
//
//        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
//        response.setStatus(401);
//        response.setContentType(CONTENT_TYPE);
//
//    //     Map<String, Object> body = new HashMap<>();
//    //     body.put("message", "Error en la autenticacion username o password incorrecto!");
//    //     body.put("error", failed.getMessage());
//    //     response.getWriter().write(new ObjectMapper().writeValueAsString(body));
//    //     response.setStatus(401);
//    //     response.setContentType("application/json");
//    }



}
