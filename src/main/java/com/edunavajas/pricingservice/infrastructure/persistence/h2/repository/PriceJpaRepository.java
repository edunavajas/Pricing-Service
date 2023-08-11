package com.edunavajas.pricingservice.infrastructure.persistence.h2.repository;

import com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.entities.PriceEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity,Long> {

    List<PriceEntity> findAll(Specification<PriceEntity> spec);

}
