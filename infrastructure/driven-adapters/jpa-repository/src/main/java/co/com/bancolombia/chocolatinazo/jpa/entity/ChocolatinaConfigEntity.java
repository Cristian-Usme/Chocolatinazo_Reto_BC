package co.com.bancolombia.chocolatinazo.jpa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "chocolatina_config")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChocolatinaConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
