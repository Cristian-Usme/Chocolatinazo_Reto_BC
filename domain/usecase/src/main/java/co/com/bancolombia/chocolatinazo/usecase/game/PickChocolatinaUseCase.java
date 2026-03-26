package co.com.bancolombia.chocolatinazo.usecase.game;

import co.com.bancolombia.chocolatinazo.model.game.GameRecord;
import co.com.bancolombia.chocolatinazo.model.game.gateway.GameRecordRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class PickChocolatinaUseCase {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 320;

    private final GameRecordRepository gameRecordRepository;

    public GameRecord execute(UUID userId) {
        gameRecordRepository.findByUserId(userId).ifPresent(existing -> {
            throw new IllegalArgumentException("User already picked a chocolatina in this round");
        });

        int randomNumber = ThreadLocalRandom.current().nextInt(MIN_NUMBER, MAX_NUMBER + 1);

        GameRecord record = GameRecord.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .chocolatinaNumber(randomNumber)
                .pickedAt(LocalDateTime.now())
                .build();

        return gameRecordRepository.save(record);
    }
}