package com.edunavajas.pricingservice.application.ports.outgoing.repositories;

import com.edunavajas.pricingservice.domain.model.entities.Price;

import java.time.OffsetDateTime;

public interface PriceRepository {
    Price findByCriteria(OffsetDateTime dateTime, Integer productId, Integer brandId);
}
