package co.com.bancolombia.chocolatinazo.model.game.gateway;


import co.com.bancolombia.chocolatinazo.model.game.ChocolatinaConfig;

import java.util.Optional;

public interface ChocolatinaConfigRepository {
    ChocolatinaConfig save(ChocolatinaConfig config);
    Optional<ChocolatinaConfig> find();
}