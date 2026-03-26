package co.com.bancolombia.chocolatinazo.config;

import co.com.bancolombia.chocolatinazo.model.game.gateway.ChocolatinaConfigRepository;
import co.com.bancolombia.chocolatinazo.model.game.gateway.FinishedGameRepository;
import co.com.bancolombia.chocolatinazo.model.game.gateway.GameRecordRepository;
import co.com.bancolombia.chocolatinazo.model.user.gateway.PasswordEncryptor;
import co.com.bancolombia.chocolatinazo.model.user.gateway.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class UseCasesConfigTest {

    @Test
    void testUseCaseBeansExist() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class)) {
            String[] beanNames = context.getBeanDefinitionNames();

            boolean useCaseBeanFound = false;
            for (String beanName : beanNames) {
                if (beanName.endsWith("UseCase")) {
                    useCaseBeanFound = true;
                    break;
                }
            }

            assertTrue(useCaseBeanFound, "No beans ending with 'UseCase' were found");
        }
    }

    @Configuration
    @Import(UseCasesConfig.class)
    static class TestConfig {

        @Bean
        public UserRepository userRepository() {
            return mock(UserRepository.class);
        }

        @Bean
        public PasswordEncryptor passwordEncryptor() {
            return mock(PasswordEncryptor.class);
        }

        @Bean
        public GameRecordRepository gameRecordRepository() {
            return mock(GameRecordRepository.class);
        }

        @Bean
        public FinishedGameRepository finishedGameRepository() {
            return mock(FinishedGameRepository.class);
        }

        @Bean
        public ChocolatinaConfigRepository chocolatinaConfigRepository() {
            return mock(ChocolatinaConfigRepository.class);
        }
    }
}