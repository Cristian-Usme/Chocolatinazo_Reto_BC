package co.com.bancolombia.chocolatinazo.usecase.game;

import co.com.bancolombia.chocolatinazo.model.game.FinishedGame;
import co.com.bancolombia.chocolatinazo.model.game.gateway.FinishedGameRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetFinishedGamesUseCase {

    private final FinishedGameRepository finishedGameRepository;

    public List<FinishedGame> execute() {
        return finishedGameRepository.findAll();
    }
}