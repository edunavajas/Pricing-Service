package com.edunavajas.pricingservice.interfaces.rest;

import com.edunavajas.pricingservice.application.ports.incoming.PriceService;
import com.edunavajas.pricingservice.interfaces.rest.mappers.PriceMapper;
import com.edunavajas.pricingservice.openapi.interfaces.rest.PricesApi;
import com.edunavajas.pricingservice.openapi.interfaces.rest.dtos.PriceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@AllArgsConstructor
public class PriceController implements PricesApi {

    private final PriceService priceService;
    private final PriceMapper priceMapper;

    @Override
    public ResponseEntity<PriceResponse> pricesGet(OffsetDateTime dateTime, Integer productId, Integer brandId) {
        return ResponseEntity.ok(priceMapper.toResponse(priceService.getPriceByCriteria(dateTime, productId, brandId)));
    }
}
