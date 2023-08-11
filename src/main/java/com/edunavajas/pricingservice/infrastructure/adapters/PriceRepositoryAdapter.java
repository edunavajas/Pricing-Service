package com.edunavajas.pricingservice.infrastructure.adapters;

import com.edunavajas.pricingservice.application.ports.outgoing.repositories.PriceRepository;
import com.edunavajas.pricingservice.domain.model.entities.Price;
import com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.entities.PriceEntity;
import com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.mappers.PriceEntityMapper;
import com.edunavajas.pricingservice.infrastructure.persistence.h2.repository.PriceJpaRepository;
import com.edunavajas.pricingservice.infrastructure.specification.PriceSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    private final PriceEntityMapper priceEntityMapper;

    public Price findByCriteria(OffsetDateTime dateTime, Integer productId, Integer brandId) {
        List<PriceEntity> results = findPricesByCriteria(dateTime, productId, brandId);
        return getHighestPriorityPrice(results);
    }

    private List<PriceEntity> findPricesByCriteria(OffsetDateTime dateTime, Integer productId, Integer brandId) {
        PriceSpecification specification = new PriceSpecification(dateTime, productId, brandId);
        return priceJpaRepository.findAll(specification);
    }

    private Price getHighestPriorityPrice(List<PriceEntity> results) {
        return results.stream()
                .findFirst()
                .map(priceEntityMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("No price found for the given criteria"));
    }
}
