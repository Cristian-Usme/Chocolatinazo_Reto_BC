package co.com.bancolombia.chocolatinazo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameRecordResponse {
    private UUID id;
    private UUID userId;
    private int chocolatinaNumber;
    private LocalDateTime pickedAt;
}