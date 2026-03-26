package co.com.bancolombia.chocolatinazo.usecase.game;

import co.com.bancolombia.chocolatinazo.model.game.ChocolatinaConfig;
import co.com.bancolombia.chocolatinazo.model.game.FinishedGame;
import co.com.bancolombia.chocolatinazo.model.game.GameRecord;
import co.com.bancolombia.chocolatinazo.model.game.gateway.ChocolatinaConfigRepository;
import co.com.bancolombia.chocolatinazo.model.game.gateway.FinishedGameRepository;
import co.com.bancolombia.chocolatinazo.model.game.gateway.GameRecordRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CalculateLoserUseCase {

    private final GameRecordRepository gameRecordRepository;
    private final FinishedGameRepository finishedGameRepository;
    private final ChocolatinaConfigRepository chocolatinaConfigRepository;

    public FinishedGame execute(String rule) {
        List<GameRecord> records = gameRecordRepository.findAll();
        if (records.isEmpty()) {
            throw new IllegalArgumentException("There are no records to calculate loser");
        }

        ChocolatinaConfig config = chocolatinaConfigRepository.find()
                .orElseThrow(() -> new IllegalArgumentException("Chocolatina price configuration is missing"));

        GameRecord loserRecord = selectLoserRecord(records, rule);
        BigDecimal totalPaid = config.getPrice().multiply(BigDecimal.valueOf(records.size()));

        FinishedGame finishedGame = FinishedGame.builder()
                .id(UUID.randomUUID())
                .loserId(loserRecord.getUserId())
                .losingNumber(loserRecord.getChocolatinaNumber())
                .chocolatinasPlayed(records.size())
                .chocolatinaPrice(config.getPrice())
                .totalPaid(totalPaid)
                .finishedAt(LocalDateTime.now())
                .build();

        FinishedGame savedGame = finishedGameRepository.save(finishedGame);
        gameRecordRepository.deleteAll();
        return savedGame;
    }

    private GameRecord selectLoserRecord(List<GameRecord> records, String rule) {
        String normalizedRule = rule == null ? "" : rule.trim().toUpperCase();

        return switch (normalizedRule) {
            case "HIGHEST" -> records.stream()
                    .max(Comparator.comparingInt(GameRecord::getChocolatinaNumber))
                    .orElseThrow();
            case "LOWEST" -> records.stream()
                    .min(Comparator.comparingInt(GameRecord::getChocolatinaNumber))
                    .orElseThrow();
            default -> throw new IllegalArgumentException("Rule must be HIGHEST or LOWEST");
        };
    }
}