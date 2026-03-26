package co.com.bancolombia.chocolatinazo.config;

import co.com.bancolombia.chocolatinazo.model.game.gateway.ChocolatinaConfigRepository;
import co.com.bancolombia.chocolatinazo.model.game.gateway.FinishedGameRepository;
import co.com.bancolombia.chocolatinazo.model.game.gateway.GameRecordRepository;
import co.com.bancolombia.chocolatinazo.usecase.game.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.bancolombia.chocolatinazo.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

        @Bean
        public PickChocolatinaUseCase pickChocolatinaUseCase(GameRecordRepository gameRecordRepository) {
                return new PickChocolatinaUseCase(gameRecordRepository);
        }

        @Bean
        public UpdatePriceUseCase updatePriceUseCase(ChocolatinaConfigRepository chocolatinaConfigRepository) {
                return new UpdatePriceUseCase(chocolatinaConfigRepository);
        }

        @Bean
        public CalculateLoserUseCase calculateLoserUseCase(
                GameRecordRepository gameRecordRepository,
                FinishedGameRepository finishedGameRepository,
                ChocolatinaConfigRepository chocolatinaConfigRepository
        ) {
                return new CalculateLoserUseCase(gameRecordRepository, finishedGameRepository, chocolatinaConfigRepository);
        }

        @Bean
        public GetCurrentGameUseCase getCurrentGameUseCase(GameRecordRepository gameRecordRepository) {
                return new GetCurrentGameUseCase(gameRecordRepository);
        }

        @Bean
        public GetFinishedGamesUseCase getFinishedGamesUseCase(FinishedGameRepository finishedGameRepository) {
                return new GetFinishedGamesUseCase(finishedGameRepository);
        }
}
