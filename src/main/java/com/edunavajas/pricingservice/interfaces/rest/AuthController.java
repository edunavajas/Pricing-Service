package com.edunavajas.pricingservice.interfaces.rest;

import com.edunavajas.pricingservice.application.ports.incoming.AuthService;
import com.edunavajas.pricingservice.interfaces.rest.mappers.AuthMapper;
import com.edunavajas.pricingservice.openapi.interfaces.rest.AuthApi;
import com.edunavajas.pricingservice.openapi.interfaces.rest.dtos.AuthenticationRequest;
import com.edunavajas.pricingservice.openapi.interfaces.rest.dtos.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController implements AuthApi {

    private final AuthService authService;
    private final AuthMapper authMapper;

    @Override
    public ResponseEntity<AuthenticationResponse> authenticateUser(AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse response = authMapper.toResponse(authService.authenticate(authMapper.toDTO(authenticationRequest)));
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
