package com.edunavajas.pricingservice.application.services;


import com.edunavajas.pricingservice.application.ports.incoming.PriceService;
import com.edunavajas.pricingservice.application.ports.outgoing.repositories.PriceRepository;
import com.edunavajas.pricingservice.domain.model.entities.Price;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class PriceDefaultService implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Price getPriceByCriteria(OffsetDateTime dateTime, Integer productId, Integer brandId) {
        return priceRepository.findByCriteria(dateTime, productId, brandId);
    }
}
