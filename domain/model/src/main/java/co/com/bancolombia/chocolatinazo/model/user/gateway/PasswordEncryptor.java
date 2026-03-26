package co.com.bancolombia.chocolatinazo.model.user.gateway;

public interface PasswordEncryptor {

    String encrypt(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
