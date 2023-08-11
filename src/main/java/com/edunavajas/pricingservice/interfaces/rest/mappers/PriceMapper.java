package com.edunavajas.pricingservice.interfaces.rest.mappers;

import com.edunavajas.pricingservice.domain.model.entities.Price;
import com.edunavajas.pricingservice.openapi.interfaces.rest.dtos.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "toOffsetDateTime")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "toOffsetDateTime")
    PriceResponse toResponse(Price price);

    @Named("toOffsetDateTime")
    default OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atOffset(ZoneOffset.UTC);
    }

    @Named("toLocalDateTime")
    default LocalDateTime toLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? null : offsetDateTime.toLocalDateTime();
    }
}

