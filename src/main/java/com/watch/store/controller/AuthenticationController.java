package com.watch.store.controller;

import com.nimbusds.jose.JOSEException;
import com.watch.store.dto.request.AuthenticationRequestDTO;
import com.watch.store.dto.request.IntrospectRequestDTO;
import com.watch.store.dto.request.LogoutRequestDTO;
import com.watch.store.dto.request.RefreshRequestDTO;
import com.watch.store.dto.response.AuthenticationResponseDTO;
import com.watch.store.dto.response.IntrospectResponseDTO;
import com.watch.store.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        AuthenticationResponseDTO result = authenticationService.authenticate(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/introspect")
    public ResponseEntity<IntrospectResponseDTO> authenticate(@RequestBody IntrospectRequestDTO request)
            throws ParseException, JOSEException {
        IntrospectResponseDTO result = authenticationService.introspect(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody RefreshRequestDTO request)
            throws ParseException, JOSEException {
        AuthenticationResponseDTO result = authenticationService.refreshToken(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ResponseEntity.noContent().build();
    }
}
