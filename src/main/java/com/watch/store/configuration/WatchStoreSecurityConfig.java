package com.watch.store.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WatchStoreSecurityConfig {
    private final String[] PUBLIC_ENDPOINTS = {
            "/users", "/auth/token", "/auth/introspect", "/auth/logout", "/auth/refresh"
    };

    private final CustomJwtDecoder customJwtDecoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS)
                .permitAll()
                .requestMatchers(HttpMethod.POST,"/api/send-email").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/users").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET,"/api/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/products").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/products").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/comments").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT,"/api/comments").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE,"/api/comments/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST,"/api/product-discounts").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/product-discounts").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/product-discounts/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/product-discounts").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/orders").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE,"/api/orders/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET,"/api/orders").hasRole("ADMIN")
                .anyRequest()
                .permitAll());

        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(customJwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
}
