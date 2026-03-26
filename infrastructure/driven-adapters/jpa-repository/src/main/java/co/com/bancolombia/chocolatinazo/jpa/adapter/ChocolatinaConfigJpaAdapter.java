package co.com.bancolombia.chocolatinazo.jpa.adapter;

import co.com.bancolombia.chocolatinazo.jpa.mapper.ChocolatinaConfigMapper;
import co.com.bancolombia.chocolatinazo.jpa.repository.ChocolatinaConfigJpaRepository;
import co.com.bancolombia.chocolatinazo.model.game.ChocolatinaConfig;
import co.com.bancolombia.chocolatinazo.model.game.gateway.ChocolatinaConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChocolatinaConfigJpaAdapter implements ChocolatinaConfigRepository {

    private final ChocolatinaConfigJpaRepository jpaRepository;

    @Override
    public ChocolatinaConfig save(ChocolatinaConfig config){
        var entity = ChocolatinaConfigMapper.toEntity(config);
        var saved = jpaRepository.save(entity);
        return ChocolatinaConfigMapper.toDomain(saved);
    }

    @Override
    public Optional<ChocolatinaConfig> find(){
        var all = jpaRepository.findAll();
        if (all.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(ChocolatinaConfigMapper.toDomain(all.getFirst()));
    }
}
