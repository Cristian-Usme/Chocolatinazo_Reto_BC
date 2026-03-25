package co.com.bancolombia.chocolatinazo.model.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class ChocolatinaConfig {
    private UUID id;
    private BigDecimal price;
}
