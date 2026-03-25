package co.com.bancolombia.chocolatinazo.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String username;
    private String password;
    private Role role;
    private LocalDateTime createdAt;
}