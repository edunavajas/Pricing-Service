package com.edunavajas.pricingservice.infrastructure.persistence.h2.repository;

import com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findOneWithAuthoritiesByUsernameIgnoreCase(String username);
}
