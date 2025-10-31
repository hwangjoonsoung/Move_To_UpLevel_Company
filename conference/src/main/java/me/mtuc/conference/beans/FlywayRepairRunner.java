package me.mtuc.conference.beans;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class FlywayRepairRunner {

//    @Bean
//    public FlywayMigrationStrategy flywayMigrationStrategy() {
//        return flyway -> {
//            flyway.repair();
//            flyway.migrate();
//        };
//    }

}
