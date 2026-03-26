package co.com.bancolombia.chocolatinazo.jpa.repository;

import co.com.bancolombia.chocolatinazo.jpa.entity.FinishedGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FinishedGameJpaRepository extends JpaRepository<FinishedGameEntity, UUID> {
}
