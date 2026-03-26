package co.com.bancolombia.chocolatinazo.api;

import co.com.bancolombia.chocolatinazo.api.dto.CalculateLoserRequest;
import co.com.bancolombia.chocolatinazo.api.dto.FinishedGameResponse;
import co.com.bancolombia.chocolatinazo.api.dto.GameRecordResponse;
import co.com.bancolombia.chocolatinazo.api.dto.UpdatePriceRequest;
import co.com.bancolombia.chocolatinazo.api.dto.UpdatePriceResponse;
import co.com.bancolombia.chocolatinazo.model.game.ChocolatinaConfig;
import co.com.bancolombia.chocolatinazo.model.game.FinishedGame;
import co.com.bancolombia.chocolatinazo.model.game.GameRecord;
import co.com.bancolombia.chocolatinazo.usecase.game.CalculateLoserUseCase;
import co.com.bancolombia.chocolatinazo.usecase.game.PickChocolatinaUseCase;
import co.com.bancolombia.chocolatinazo.usecase.game.UpdatePriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/game")
@RequiredArgsConstructor
public class GameController {

    private final PickChocolatinaUseCase pickChocolatinaUseCase;
    private final UpdatePriceUseCase updatePriceUseCase;
    private final CalculateLoserUseCase calculateLoserUseCase;

    @PostMapping(path = "/pick")
    @PreAuthorize("hasRole('PLAYER')")
    public ResponseEntity<GameRecordResponse> pick(Authentication authentication) {
        try {
            UUID userId = UUID.fromString(authentication.getPrincipal().toString());
            GameRecord record = pickChocolatinaUseCase.execute(userId);
            return ResponseEntity.ok(toGameRecordResponse(record));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/price")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdatePriceResponse> updatePrice(@RequestBody UpdatePriceRequest request) {
        try {
            ChocolatinaConfig updatedConfig = updatePriceUseCase.execute(request.getPrice());
            return ResponseEntity.ok(UpdatePriceResponse.builder()
                    .id(updatedConfig.getId())
                    .price(updatedConfig.getPrice())
                    .build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/calculate-loser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FinishedGameResponse> calculateLoser(@RequestBody CalculateLoserRequest request) {
        try {
            FinishedGame finishedGame = calculateLoserUseCase.execute(request.getRule());
            return ResponseEntity.status(HttpStatus.CREATED).body(toFinishedGameResponse(finishedGame));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
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