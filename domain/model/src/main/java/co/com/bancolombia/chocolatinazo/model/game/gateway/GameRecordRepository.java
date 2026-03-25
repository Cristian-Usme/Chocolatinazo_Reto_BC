package co.com.bancolombia.chocolatinazo.model.game.gateway;

import co.com.bancolombia.chocolatinazo.model.game.GameRecord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRecordRepository {
    GameRecord save(GameRecord record);
    Optional<GameRecord> findByUserId(UUID userId);
    List<GameRecord> findAll();
    void deleteAll();
}
