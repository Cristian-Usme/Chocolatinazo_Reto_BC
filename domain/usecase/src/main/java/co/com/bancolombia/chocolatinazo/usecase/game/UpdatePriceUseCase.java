package co.com.bancolombia.chocolatinazo.usecase.game;

import co.com.bancolombia.chocolatinazo.model.game.ChocolatinaConfig;
import co.com.bancolombia.chocolatinazo.model.game.gateway.ChocolatinaConfigRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
public class UpdatePriceUseCase {

    private final ChocolatinaConfigRepository chocolatinaConfigRepository;

    public ChocolatinaConfig execute(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to zero");
        }

        ChocolatinaConfig currentConfig = chocolatinaConfigRepository.find()
                .orElse(ChocolatinaConfig.builder()
                        .id(UUID.randomUUID())
                        .price(newPrice)
                        .build());

        ChocolatinaConfig updatedConfig = ChocolatinaConfig.builder()
                .id(currentConfig.getId())
                .price(newPrice)
                .build();

        return chocolatinaConfigRepository.save(updatedConfig);
    }
}