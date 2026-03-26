package co.com.bancolombia.chocolatinazo.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class UpdatePriceRequest {
    private BigDecimal price;
}