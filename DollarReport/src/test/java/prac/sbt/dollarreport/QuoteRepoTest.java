package prac.sbt.dollarreport;

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
        assert quoteRepo.findByDate(today) != null;
        assert quoteRepo.findByDate(today.plusDays(1)) == null;
        assert quoteRepo.findByDate(today.minusDays(1)).getValue() == 150;
    }
}