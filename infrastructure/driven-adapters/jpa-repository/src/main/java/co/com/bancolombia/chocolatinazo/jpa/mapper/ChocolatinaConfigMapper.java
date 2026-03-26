package co.com.bancolombia.chocolatinazo.jpa.mapper;

import co.com.bancolombia.chocolatinazo.jpa.entity.ChocolatinaConfigEntity;
import co.com.bancolombia.chocolatinazo.model.game.ChocolatinaConfig;

public class ChocolatinaConfigMapper {

    private ChocolatinaConfigMapper(){
    }

    public static ChocolatinaConfig toDomain(ChocolatinaConfigEntity entity){
        if(entity == null) return null;
        return ChocolatinaConfig.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .build();
    }

    public static ChocolatinaConfigEntity toEntity(ChocolatinaConfig domain){
        if(domain == null) return null;
        return ChocolatinaConfigEntity.builder()
                .id(domain.getId())
                .price(domain.getPrice())
                .build();
    }
}
