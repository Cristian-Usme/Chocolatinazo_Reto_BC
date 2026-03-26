package co.com.bancolombia.chocolatinazo.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "current_game_records")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "chocolatina_number", nullable = false)
    private int chocolatinaNumber;

    @Column(name = "picked_at", nullable = false)
    private LocalDateTime pickedAt;
}
