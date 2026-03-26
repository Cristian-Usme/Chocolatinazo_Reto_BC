package co.com.bancolombia.chocolatinazo.usecase.auth;

import co.com.bancolombia.chocolatinazo.model.user.User;
import co.com.bancolombia.chocolatinazo.model.user.gateway.PasswordEncryptor;
import co.com.bancolombia.chocolatinazo.model.user.gateway.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public User execute(String username, String rawPassword, String role) {

        userRepository.findByUsername(username).ifPresent(existing -> {
            throw new IllegalArgumentException("Username '" + username + "' already exists");
        });

        User user = User.builder()
                .id(UUID.randomUUID())
                .username(username)
                .password(passwordEncryptor.encrypt(rawPassword))
                .role(co.com.bancolombia.chocolatinazo.model.user.Role.valueOf(role.toUpperCase()))
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }
}