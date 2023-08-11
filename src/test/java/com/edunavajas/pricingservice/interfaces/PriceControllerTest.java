package com.edunavajas.pricingservice.interfaces;

import com.edunavajas.pricingservice.application.ports.incoming.PriceService;
import com.edunavajas.pricingservice.domain.exceptions.CustomExceptionHandler;
import com.edunavajas.pricingservice.domain.model.entities.Price;
import com.edunavajas.pricingservice.interfaces.rest.PriceController;
import com.edunavajas.pricingservice.interfaces.rest.mappers.PriceMapperImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes = PriceController.class)
@Import({PriceMapperImpl.class, CustomExceptionHandler.class})
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    // Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void shouldRetrieveDefaultPriceForMorningOf14th() throws Exception {
        Price price = Price.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .curr("EUR")
                .build();

        when(priceService.getPriceByCriteria(any(), any(), any())).thenReturn(price);

        mockMvc.perform(get("/prices")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .param("dateTime", "2020-06-14T10:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(35.50));

        verify(priceService).getPriceByCriteria(any(), any(), any());
        verifyNoMoreInteractions(priceService);
    }

    // Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void shouldRetrieveDiscountedPriceForAfternoonOf14th() throws Exception {
        Price price = Price.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30))
                .priceList(2)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal("25.45"))
                .curr("EUR")
                .build();

        when(priceService.getPriceByCriteria(any(), any(), any())).thenReturn(price);

        mockMvc.perform(get("/prices")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .param("dateTime", "2020-06-14T16:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(25.45));

        verify(priceService).getPriceByCriteria(any(), any(), any());
        verifyNoMoreInteractions(priceService);
    }

    // Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void shouldRetrieveDefaultPriceForNightOf14th() throws Exception {
        Price price = Price.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .curr("EUR")
                .build();

        when(priceService.getPriceByCriteria(any(), any(), any())).thenReturn(price);

        mockMvc.perform(get("/prices")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .param("dateTime", "2020-06-14T21:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(35.50));

        verify(priceService).getPriceByCriteria(any(), any(), any());
        verifyNoMoreInteractions(priceService);
    }

    // Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void shouldRetrieveMorningDiscountFor15th() throws Exception {
        Price price = Price.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 15, 11, 0))
                .priceList(3)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal("30.50"))
                .curr("EUR")
                .build();

        when(priceService.getPriceByCriteria(any(), any(), any())).thenReturn(price);

        mockMvc.perform(get("/prices")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .param("dateTime", "2020-06-15T10:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(30.50));

        verify(priceService).getPriceByCriteria(any(), any(), any());
        verifyNoMoreInteractions(priceService);
    }

    // Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void shouldRetrieveDefaultPriceForNightOf16th() throws Exception {
        Price price = Price.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 15, 16, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(4)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal("38.95"))
                .curr("EUR")
                .build();

        when(priceService.getPriceByCriteria(any(), any(), any())).thenReturn(price);

        mockMvc.perform(get("/prices")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .param("dateTime", "2020-06-16T21:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(38.95));

        verify(priceService).getPriceByCriteria(any(), any(), any());
        verifyNoMoreInteractions(priceService);
    }

    @Test
    void shouldReturnNotFoundWhenPriceIsNotAvailable() throws Exception {
        when(priceService.getPriceByCriteria(any(), any(), any())).thenThrow(new EntityNotFoundException("No price found for the given criteria"));

        mockMvc.perform(get("/prices")
                        .with(user("admin").password("admin").roles("ADMIN"))
                        .param("dateTime", "2020-06-14T10:00:00Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound());

        verify(priceService).getPriceByCriteria(any(), any(), any());
        verifyNoMoreInteractions(priceService);
    }


}
