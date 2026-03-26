package co.com.bancolombia.chocolatinazo.usecase.game;

import co.com.bancolombia.chocolatinazo.model.game.GameRecord;
import co.com.bancolombia.chocolatinazo.model.game.gateway.GameRecordRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetCurrentGameUseCase {

    private final GameRecordRepository gameRecordRepository;

    public List<GameRecord> execute() {
        return gameRecordRepository.findAll();
    }
}