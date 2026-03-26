package co.com.bancolombia.chocolatinazo.jpa.adapter;

import co.com.bancolombia.chocolatinazo.jpa.mapper.FinishedGameMapper;
import co.com.bancolombia.chocolatinazo.jpa.repository.FinishedGameJpaRepository;
import co.com.bancolombia.chocolatinazo.model.game.FinishedGame;
import co.com.bancolombia.chocolatinazo.model.game.gateway.FinishedGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FinishedGameJpaAdapter implements FinishedGameRepository {

    private final FinishedGameJpaRepository jpaRepository;

    @Override
    public FinishedGame save(FinishedGame finishedGame){
        var entity = FinishedGameMapper.toEntity(finishedGame);
        var saved = jpaRepository.save(entity);
        return FinishedGameMapper.toDomain(saved);
    }

    @Override
    public List<FinishedGame> findAll(){
        return jpaRepository.findAll().stream()
                .map(FinishedGameMapper::toDomain)
                .toList();
    }
}
