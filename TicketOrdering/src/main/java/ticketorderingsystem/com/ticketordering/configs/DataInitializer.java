package ticketorderingsystem.com.ticketordering.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ticketorderingsystem.com.ticketordering.models.StationModel;
import ticketorderingsystem.com.ticketordering.repositories.StationRepository;

@Slf4j
@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner loadData(StationRepository stationRepository) {
        return args -> {
            if (stationRepository.count() == 0) {
                stationRepository.save(new StationModel(0, "ВДНХ"));
                stationRepository.save(new StationModel(0, "Алексеевская"));
                stationRepository.save(new StationModel(0, "Тургеневская"));
                stationRepository.save(new StationModel(0, "Чистые пруды"));
                stationRepository.save(new StationModel(0, "Чкаловская"));
                stationRepository.save(new StationModel(0, "Одинцово"));
                stationRepository.save(new StationModel(0, "Китай-город"));
                stationRepository.save(new StationModel(0, "Проспект мира"));
                log.info("Test data has been inserted successfully.");
            } else {
                log.info("Test data already exists.");
            }
        };
    }
}
