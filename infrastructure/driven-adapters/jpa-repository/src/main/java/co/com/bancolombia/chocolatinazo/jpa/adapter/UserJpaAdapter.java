package co.com.bancolombia.chocolatinazo.jpa.adapter;

import co.com.bancolombia.chocolatinazo.jpa.mapper.UserMapper;
import co.com.bancolombia.chocolatinazo.jpa.repository.UserJpaRepository;
import co.com.bancolombia.chocolatinazo.model.user.User;
import co.com.bancolombia.chocolatinazo.model.user.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user){
        var entity = UserMapper.toEntity(user);
        var saved = jpaRepository.save(entity);
        return UserMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findByUsername(String username){
        return jpaRepository.findByUsername(username)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id){
        return jpaRepository.findById(id)
                .map(UserMapper::toDomain);
    }
}
