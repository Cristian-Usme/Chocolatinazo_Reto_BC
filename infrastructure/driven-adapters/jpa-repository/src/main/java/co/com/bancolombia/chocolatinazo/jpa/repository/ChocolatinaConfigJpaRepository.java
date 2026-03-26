package co.com.bancolombia.chocolatinazo.jpa.repository;

import co.com.bancolombia.chocolatinazo.jpa.entity.ChocolatinaConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChocolatinaConfigJpaRepository extends JpaRepository<ChocolatinaConfigEntity, UUID> {
}
