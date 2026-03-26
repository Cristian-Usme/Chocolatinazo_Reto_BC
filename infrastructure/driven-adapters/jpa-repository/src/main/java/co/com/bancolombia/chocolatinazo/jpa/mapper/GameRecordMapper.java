package co.com.bancolombia.chocolatinazo.jpa.mapper;

import co.com.bancolombia.chocolatinazo.jpa.entity.GameRecordEntity;
import co.com.bancolombia.chocolatinazo.model.game.GameRecord;

public class GameRecordMapper {

    private GameRecordMapper(){
    }

    public static GameRecord toDomain(GameRecordEntity entity){
        if(entity == null) return null;
        return GameRecord.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .chocolatinaNumber(entity.getChocolatinaNumber())
                .pickedAt(entity.getPickedAt())
                .build();
    }

    public static GameRecordEntity toEntity(GameRecord domain){
        if(domain == null) return null;
        return GameRecordEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .chocolatinaNumber(domain.getChocolatinaNumber())
                .pickedAt(domain.getPickedAt())
                .build();
    }
}
