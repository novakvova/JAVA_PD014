package program;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import program.iterfaces.SeedService;
import program.storage.StorageProperties;
import program.storage.StorageService;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello String Boot!");
        SpringApplication.run(Main.class, args);
    }
    @Bean
    CommandLineRunner init(StorageService storageService, SeedService seedService) {
        return (args) -> {
          try {
              storageService.init();
              seedService.seedRoleData();

          } catch(Exception ex) {
              System.out.println("---Хюсто у нас проблеми---"+ ex.getMessage());
          }
        };
    }

}
