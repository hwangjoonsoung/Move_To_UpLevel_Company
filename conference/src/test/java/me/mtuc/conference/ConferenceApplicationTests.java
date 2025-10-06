package me.mtuc.conference;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class ConferenceApplicationTests {

    @Test
    void contextLoads() {

        LocalDate yyyyMMdd = LocalDate.parse("19990909", DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(yyyyMMdd);

    }

}
