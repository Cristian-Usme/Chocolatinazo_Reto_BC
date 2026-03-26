package co.com.bancolombia.chocolatinazo.jpa.mapper;

import co.com.bancolombia.chocolatinazo.jpa.entity.FinishedGameEntity;
import co.com.bancolombia.chocolatinazo.model.game.FinishedGame;

public class FinishedGameMapper {

    private FinishedGameMapper(){
    }

    public static FinishedGame toDomain(FinishedGameEntity entity){
        if(entity == null) return null;
        return FinishedGame.builder()
                .id(entity.getId())
                .loserId(entity.getLoserId())
                .losingNumber(entity.getLosingNumber())
                .chocolatinasPlayed(entity.getChocolatinasPlayed())
                .chocolatinaPrice(entity.getChocolatinaPrice())
                .totalPaid(entity.getTotalPaid())
                .finishedAt(entity.getFinishedAt())
                .build();
    }

    public static FinishedGameEntity toEntity(FinishedGame domain){
        if(domain == null) return null;
        return FinishedGameEntity.builder()
                .id(domain.getId())
                .loserId(domain.getLoserId())
                .losingNumber(domain.getLosingNumber())
                .chocolatinasPlayed(domain.getChocolatinasPlayed())
                .chocolatinaPrice(domain.getChocolatinaPrice())
                .totalPaid(domain.getTotalPaid())
                .finishedAt(domain.getFinishedAt())
                .build();
    }
}
