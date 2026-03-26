package co.com.bancolombia.chocolatinazo.jpa.repository;

import co.com.bancolombia.chocolatinazo.jpa.entity.GameRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GameRecordJpaRepository extends JpaRepository<GameRecordEntity, UUID> {

    Optional<GameRecordEntity> findByUserId(UUID userId);
}
