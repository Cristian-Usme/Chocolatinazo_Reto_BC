package co.com.bancolombia.chocolatinazo.api.security;

import co.com.bancolombia.chocolatinazo.model.user.gateway.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BCryptPasswordEncryptorAdapter implements PasswordEncryptor {

    private final BCryptPasswordEncoder encoder;

    @Override
    public String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
