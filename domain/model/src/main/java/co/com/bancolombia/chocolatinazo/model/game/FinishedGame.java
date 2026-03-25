package co.com.bancolombia.chocolatinazo.model.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class FinishedGame {
    private UUID id;
    private UUID loserId;
    private int losingNumber;
    private int chocolatinasPlayed;
    private BigDecimal chocolatinaPrice;
    private BigDecimal totalPaid;
    private LocalDateTime finishedAt;
}
