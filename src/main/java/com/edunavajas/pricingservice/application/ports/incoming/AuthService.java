package com.edunavajas.pricingservice.application.ports.incoming;


import com.edunavajas.pricingservice.domain.model.entities.Auth;
import com.edunavajas.pricingservice.domain.model.entities.AuthJwt;

public interface AuthService {
    AuthJwt authenticate(Auth auth);
}
