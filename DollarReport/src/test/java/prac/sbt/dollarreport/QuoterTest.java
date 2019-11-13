package prac.sbt.dollarreport;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@SpringBootTest
class QuoterTest {

    @InjectMocks
    @Autowired
    Quoter quote;

    @MockBean
    private HttpReader httpReader;
    @MockBean
    private QuoteRepo quoteRepo;

    List<String> testLines = List.of("USD000000TOD\t2019-09-17\t64.03\t64.44\t63.97\t64.3575\t725278000\t64.184");
    @Test
    void getFromWeb() throws IOException {

        Mockito.when(httpReader.readHttp(Mockito.any())).thenReturn(testLines.stream());
        assert quote.getMaxQuote() == 64.184;
    }

    @Test
    void getFromRepo() throws IOException {

        Mockito.when(quoteRepo.findByDate(Mockito.any())).thenReturn(new Quote(LocalDate.now(ZoneId.of("Europe/Moscow")), 100));
        Mockito.when(quoteRepo.findAll()).thenReturn(List.of(new Quote(LocalDate.now(ZoneId.of("Europe/Moscow")), 100)));
        Mockito.when(httpReader.readHttp(Mockito.any())).thenThrow(new RuntimeException(new IllegalAccessException()));
        assert quote.getMaxQuote() == 100;
    }
}