package co.com.bancolombia.chocolatinazo.jpa.mapper;

import co.com.bancolombia.chocolatinazo.jpa.entity.UserEntity;
import co.com.bancolombia.chocolatinazo.model.user.Role;
import co.com.bancolombia.chocolatinazo.model.user.User;

public class UserMapper {

    private UserMapper(){

    }

    public static User toDomain(UserEntity entity){
        if(entity == null) return null;
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(Role.valueOf(entity.getRole().name()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static UserEntity toEntity(User domain){
        if(domain == null) return null;
        return UserEntity.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .password(domain.getPassword())
                .role(UserEntity.RoleEnum.valueOf(domain.getRole().name()))
                .createdAt(domain.getCreatedAt())
                .build();
    }
}
