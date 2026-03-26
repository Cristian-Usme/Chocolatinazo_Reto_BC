package co.com.bancolombia.chocolatinazo.api;

import co.com.bancolombia.chocolatinazo.api.dto.AuthResponse;
import co.com.bancolombia.chocolatinazo.api.dto.LoginRequest;
import co.com.bancolombia.chocolatinazo.api.dto.RegisterRequest;
import co.com.bancolombia.chocolatinazo.api.security.JwtTokenProvider;
import co.com.bancolombia.chocolatinazo.model.user.User;
import co.com.bancolombia.chocolatinazo.usecase.auth.LoginUseCase;
import co.com.bancolombia.chocolatinazo.usecase.auth.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            User user = registerUserUseCase.execute(
                    request.getUsername(),
                    request.getPassword(),
                    request.getRole()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(createAuthResponse(user));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            User user = loginUseCase.execute(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(createAuthResponse(user));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private AuthResponse createAuthResponse(User user) {
        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getUsername(),
                user.getRole().name()
        );

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}