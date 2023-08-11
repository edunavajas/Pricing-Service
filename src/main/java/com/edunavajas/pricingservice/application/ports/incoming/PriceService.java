package com.edunavajas.pricingservice.application.ports.incoming;


import com.edunavajas.pricingservice.domain.model.entities.Price;

import java.time.OffsetDateTime;

public interface PriceService {
    Price getPriceByCriteria(OffsetDateTime dateTime, Integer productId, Integer brandId);
}
