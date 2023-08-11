package com.edunavajas.pricingservice.domain.model.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@Data
public class Auth {

    String username;
    String password;
}
