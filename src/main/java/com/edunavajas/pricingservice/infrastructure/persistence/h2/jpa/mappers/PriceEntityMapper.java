package com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.mappers;

import com.edunavajas.pricingservice.domain.model.entities.Price;
import com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.entities.PriceEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PriceEntityMapper {
    Price toDTO(PriceEntity priceEntity);
}
