package com.edunavajas.pricingservice.application.services;


import com.edunavajas.pricingservice.application.ports.incoming.AuthService;
import com.edunavajas.pricingservice.domain.model.entities.Auth;
import com.edunavajas.pricingservice.domain.model.entities.AuthJwt;
import com.edunavajas.pricingservice.interfaces.rest.configuration.security.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthDefaultService implements AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public AuthJwt authenticate(Auth auth) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    auth.getUsername(), auth.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);

            return new AuthJwt(jwt);
        } catch (AuthenticationException e) {
            log.error("Failed to authenticate user: {}", auth.getUsername(), e);
            throw e;
        }
    }
}
