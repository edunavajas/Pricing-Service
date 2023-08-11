package com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "authority")
public class AuthorityEntity implements Serializable {

    @Id
    @Column(name = "name", length = 50)
    private String name;
}
