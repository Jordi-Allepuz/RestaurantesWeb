package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.auth.filters.JwtAuthenticationFilter;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.auth.filters.JwtValidationFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/api/restaurantes/page/**").hasRole("ADMIN")
//                        .requestMatchers("/api/restaurantes/**").hasRole("ADMIN")
                        .requestMatchers("/api/comentarios/**").hasRole("ADMIN")
                        .requestMatchers("/api/estadosrestaurantes/**").hasRole("ADMIN")
                        .requestMatchers("/api/estilosrestaurantes/**").hasRole("ADMIN")
                        .requestMatchers("/api/tiposaparcar/**").hasRole("ADMIN")
                        .requestMatchers("/api/tiposcartas/**").hasRole("ADMIN")
                        .requestMatchers("/api/tiposmenus/**").hasRole("ADMIN")
                        .requestMatchers("/api/tiposlocalizaciones/**").hasRole("ADMIN")
                        .requestMatchers("/api/tiposservicios/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(config -> config.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
//
//    @Bean
//    FilterRegistrationBean<CorsFilter> corsFilter() {
//        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
//                new CorsFilter(corsConfigurationSource()));
//        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return corsBean;
//    }


}
