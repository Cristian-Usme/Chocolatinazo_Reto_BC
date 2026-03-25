package co.com.bancolombia.chocolatinazo.model.user.gateway;

import co.com.bancolombia.chocolatinazo.model.user.User;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository {
    User save(User user);
    Optional<User> findUserByName(String username);
    Optional<User> findById(UUID id);
}
