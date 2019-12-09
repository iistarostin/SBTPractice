package prac.sbt.dollarreport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class QuoterTest {
    @Mock
    RBCInterface RBCInterface;
    @Mock
    QuoteRepo quoteRepo;

    @InjectMocks
    Quoter quote;

    List<Quote> testLines = List.of(new Quote(LocalDate.now(ZoneId.of("Europe/Moscow")), 64.184));

    @Test
    void getFromWeb() throws IOException {
        MockitoAnnotations.initMocks(this);
        when(RBCInterface.getQuotesFromRBC()).thenReturn(testLines.stream());
        Assertions.assertEquals(64.184, quote.getMaxQuote());
    }

    @Test
    void getFromRepo() throws IOException {
        MockitoAnnotations.initMocks(this);
        when(quoteRepo.findByDate(Mockito.any())).thenReturn(new Quote(LocalDate.now(ZoneId.of("Europe/Moscow")), 100));
        when(quoteRepo.findAll()).thenReturn(List.of(new Quote(LocalDate.now(ZoneId.of("Europe/Moscow")), 100)));
        when(RBCInterface.getQuotesFromRBC()).thenThrow(new RuntimeException(new IllegalAccessException()));
        Assertions.assertEquals(100, quote.getMaxQuote());
    }
}