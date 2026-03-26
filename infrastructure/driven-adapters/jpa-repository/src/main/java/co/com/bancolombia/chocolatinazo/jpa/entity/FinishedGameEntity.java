package co.com.bancolombia.chocolatinazo.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "finished_games")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinishedGameEntity {

    @Id
    private UUID id;

    @Column(name = "loser_id", nullable = false)
    private UUID loserId;

    @Column(name = "losing_number", nullable = false)
    private int losingNumber;

    @Column(name = "chocolatinas_played", nullable = false)
    private int chocolatinasPlayed;

    @Column(name = "chocolatina_price", nullable = false)
    private BigDecimal chocolatinaPrice;

    @Column(name = "total_paid", nullable = false)
    private BigDecimal totalPaid;

    @Column(name = "finished_at", nullable = false)
    private LocalDateTime finishedAt;
}
