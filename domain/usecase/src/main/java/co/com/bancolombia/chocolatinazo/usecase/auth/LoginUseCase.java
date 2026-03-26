package co.com.bancolombia.chocolatinazo.usecase.auth;

import co.com.bancolombia.chocolatinazo.model.user.User;
import co.com.bancolombia.chocolatinazo.model.user.gateway.PasswordEncryptor;
import co.com.bancolombia.chocolatinazo.model.user.gateway.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public User execute(String username, String rawPassword) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncryptor.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return user;
    }
}