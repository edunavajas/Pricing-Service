package com.edunavajas.pricingservice.infraestructure.adapters;

import com.edunavajas.pricingservice.domain.model.entities.Price;
import com.edunavajas.pricingservice.infrastructure.adapters.PriceRepositoryAdapter;
import com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.entities.PriceEntity;
import com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.mappers.PriceEntityMapperImpl;
import com.edunavajas.pricingservice.infrastructure.persistence.h2.repository.PriceJpaRepository;
import com.edunavajas.pricingservice.infrastructure.specification.PriceSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    private final PriceEntityMapperImpl priceMapperEntity = new PriceEntityMapperImpl();

    private PriceRepositoryAdapter priceRepositoryAdapter;

    @BeforeEach
    void setUp() {
        priceRepositoryAdapter = new PriceRepositoryAdapter(priceJpaRepository, priceMapperEntity);
    }

    // Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void testScenarioFor10amJune14() {
        PriceEntity priceEntity = PriceEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .curr("EUR")
                .build();

        when(priceJpaRepository.findAll(any(PriceSpecification.class))).thenReturn(List.of(priceEntity));

        Price price = priceRepositoryAdapter.findByCriteria(OffsetDateTime.parse("2020-06-14T10:00:00Z"), 35455, 1);

        assertEquals(priceEntity.getBrandId(), price.getBrandId());
        assertEquals(priceEntity.getPrice(), price.getPrice());

        verify(priceJpaRepository, times(1)).findAll(any(PriceSpecification.class));
        verifyNoMoreInteractions(priceJpaRepository);
    }

    // Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)

    @Test
    void testScenarioFor4pmJune14() {
        PriceEntity priceEntity = PriceEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30))
                .priceList(2)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal("25.45"))
                .curr("EUR")
                .build();

        when(priceJpaRepository.findAll(any(PriceSpecification.class))).thenReturn(List.of(priceEntity));

        Price price = priceRepositoryAdapter.findByCriteria(OffsetDateTime.parse("2020-06-14T16:00:00Z"), 35455, 1);

        assertEquals(priceEntity.getBrandId(), price.getBrandId());
        assertEquals(priceEntity.getPrice(), price.getPrice());

        verify(priceJpaRepository, times(1)).findAll(any(PriceSpecification.class));
        verifyNoMoreInteractions(priceJpaRepository);
    }

    // Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)

    @Test
    void testScenarioFor9pmJune14() {
        PriceEntity priceEntity = PriceEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .curr("EUR")
                .build();

        when(priceJpaRepository.findAll(any(PriceSpecification.class))).thenReturn(List.of(priceEntity));

        Price price = priceRepositoryAdapter.findByCriteria(OffsetDateTime.parse("2020-06-14T21:00:00Z"), 35455, 1);

        assertEquals(priceEntity.getBrandId(), price.getBrandId());
        assertEquals(priceEntity.getPrice(), price.getPrice());

        verify(priceJpaRepository, times(1)).findAll(any(PriceSpecification.class));
        verifyNoMoreInteractions(priceJpaRepository);
    }

    // Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)

    @Test
    void testScenarioFor10amJune15() {
        PriceEntity priceEntity = PriceEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 15, 11, 0))
                .priceList(3)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal("30.50"))
                .curr("EUR")
                .build();

        when(priceJpaRepository.findAll(any(PriceSpecification.class))).thenReturn(List.of(priceEntity));

        Price price = priceRepositoryAdapter.findByCriteria(OffsetDateTime.parse("2020-06-15T10:00:00Z"), 35455, 1);

        assertEquals(priceEntity.getBrandId(), price.getBrandId());
        assertEquals(priceEntity.getPrice(), price.getPrice());

        verify(priceJpaRepository, times(1)).findAll(any(PriceSpecification.class));
        verifyNoMoreInteractions(priceJpaRepository);
    }

    // Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

    @Test
    void testScenarioFor9pmJune16() {
        PriceEntity priceEntity = PriceEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 15, 16, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(4)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal("38.95"))
                .curr("EUR")
                .build();

        when(priceJpaRepository.findAll(any(PriceSpecification.class))).thenReturn(List.of(priceEntity));

        Price price = priceRepositoryAdapter.findByCriteria(OffsetDateTime.parse("2020-06-16T21:00:00Z"), 35455, 1);

        assertEquals(priceEntity.getBrandId(), price.getBrandId());
        assertEquals(priceEntity.getPrice(), price.getPrice());

        verify(priceJpaRepository, times(1)).findAll(any(PriceSpecification.class));
        verifyNoMoreInteractions(priceJpaRepository);
    }
}
