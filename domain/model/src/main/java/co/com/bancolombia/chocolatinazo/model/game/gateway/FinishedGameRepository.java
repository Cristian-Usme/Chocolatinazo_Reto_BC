package co.com.bancolombia.chocolatinazo.model.game.gateway;

import co.com.bancolombia.chocolatinazo.model.game.FinishedGame;

import java.util.List;


public interface FinishedGameRepository {
    FinishedGame save(FinishedGame finishedGame);
    List<FinishedGame> findAll();
}
