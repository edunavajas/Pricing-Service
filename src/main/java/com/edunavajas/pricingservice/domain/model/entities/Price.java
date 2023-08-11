package com.edunavajas.pricingservice.domain.model.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
@Data
public class Price {

    Long id;
    Integer brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer priceList;
    Integer productId;
    Integer priority;
    BigDecimal price;
    String curr;
}
