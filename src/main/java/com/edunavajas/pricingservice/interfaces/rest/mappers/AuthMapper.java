package com.edunavajas.pricingservice.interfaces.rest.mappers;

import com.edunavajas.pricingservice.domain.model.entities.Auth;
import com.edunavajas.pricingservice.domain.model.entities.AuthJwt;
import com.edunavajas.pricingservice.openapi.interfaces.rest.dtos.AuthenticationRequest;
import com.edunavajas.pricingservice.openapi.interfaces.rest.dtos.AuthenticationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {


    Auth toDTO(AuthenticationRequest authenticationRequest);
    AuthenticationResponse toResponse(AuthJwt authJwt);
}

