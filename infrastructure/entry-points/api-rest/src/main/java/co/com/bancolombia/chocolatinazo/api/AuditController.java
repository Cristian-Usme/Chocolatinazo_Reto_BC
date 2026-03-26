package co.com.bancolombia.chocolatinazo.api;

import co.com.bancolombia.chocolatinazo.api.dto.FinishedGameResponse;
import co.com.bancolombia.chocolatinazo.api.dto.GameRecordResponse;
import co.com.bancolombia.chocolatinazo.model.game.FinishedGame;
import co.com.bancolombia.chocolatinazo.model.game.GameRecord;
import co.com.bancolombia.chocolatinazo.usecase.game.GetCurrentGameUseCase;
import co.com.bancolombia.chocolatinazo.usecase.game.GetFinishedGamesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/audit")
@RequiredArgsConstructor
public class AuditController {

    private final GetCurrentGameUseCase getCurrentGameUseCase;
    private final GetFinishedGamesUseCase getFinishedGamesUseCase;

    @GetMapping(path = "/current-game")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<List<GameRecordResponse>> getCurrentGame() {
        List<GameRecordResponse> response = getCurrentGameUseCase.execute().stream()
                .map(this::toGameRecordResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/finished-games")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<List<FinishedGameResponse>> getFinishedGames() {
        List<FinishedGameResponse> response = getFinishedGamesUseCase.execute().stream()
                .map(this::toFinishedGameResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    private GameRecordResponse toGameRecordResponse(GameRecord record) {
        return GameRecordResponse.builder()
                .id(record.getId())
                .userId(record.getUserId())
                .chocolatinaNumber(record.getChocolatinaNumber())
                .pickedAt(record.getPickedAt())
                .build();
    }

    private FinishedGameResponse toFinishedGameResponse(FinishedGame finishedGame) {
        return FinishedGameResponse.builder()
                .id(finishedGame.getId())
                .loserId(finishedGame.getLoserId())
                .losingNumber(finishedGame.getLosingNumber())
                .chocolatinasPlayed(finishedGame.getChocolatinasPlayed())
                .chocolatinaPrice(finishedGame.getChocolatinaPrice())
                .totalPaid(finishedGame.getTotalPaid())
                .finishedAt(finishedGame.getFinishedAt())
                .build();
    }
}