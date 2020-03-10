package prac.sbt.dollarreport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
class QuoteRepoTest {
    @Autowired
    QuoteRepo quoteRepo;

    @Test
    void storeAndGet() {
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        quoteRepo.save(new Quote(today, 100));
        quoteRepo.save(new Quote(today.minusDays(1), 150));
        Assertions.assertNotNull(quoteRepo.findByDate(today), "Missing quote");
        Assertions.assertNull(quoteRepo.findByDate(today.plusDays(1)), "Absent quote found");
        Assertions.assertEquals(150, quoteRepo.findByDate(today.minusDays(1)).getValue(), "Wrong quote value");
    }
}