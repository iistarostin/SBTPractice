package prac.sbt.dollarreport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
class RBCQuoteRepoTest {
    @Autowired
    RBCQuoteRepo rbcQuoteRepo;
    @Test
    void storeAndGet() {
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        rbcQuoteRepo.save(new RBCQuote(today, 100));
        rbcQuoteRepo.save(new RBCQuote(today.minusDays(1), 150));
        assert rbcQuoteRepo.findByDate(today) != null;
        assert rbcQuoteRepo.findByDate(today.plusDays(1)) == null;
        assert rbcQuoteRepo.findByDate(today.minusDays(1)).getValue() == 150;
    }
}