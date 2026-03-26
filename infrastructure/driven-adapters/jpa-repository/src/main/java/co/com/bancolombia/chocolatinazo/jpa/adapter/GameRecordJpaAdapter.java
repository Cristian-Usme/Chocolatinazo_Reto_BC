package co.com.bancolombia.chocolatinazo.jpa.adapter;

import co.com.bancolombia.chocolatinazo.jpa.mapper.GameRecordMapper;
import co.com.bancolombia.chocolatinazo.jpa.repository.GameRecordJpaRepository;
import co.com.bancolombia.chocolatinazo.model.game.GameRecord;
import co.com.bancolombia.chocolatinazo.model.game.gateway.GameRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class GameRecordJpaAdapter implements GameRecordRepository {

    private final GameRecordJpaRepository jpaRepository;

    @Override
    public GameRecord save(GameRecord record){
        var entity = GameRecordMapper.toEntity(record);
        var saved = jpaRepository.save(entity);
        return GameRecordMapper.toDomain(saved);
    }

    @Override
    public Optional<GameRecord> findByUserId(UUID userId){
        return jpaRepository.findByUserId(userId)
                .map(GameRecordMapper::toDomain);
    }

    @Override
    public List<GameRecord> findAll(){
        return jpaRepository.findAll().stream()
                .map(GameRecordMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteAll(){
        jpaRepository.deleteAll();
    }
}
