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
class RBCQuoterTest {

    @InjectMocks
    @Autowired
    RBCQuoter quote;

    @MockBean
    private HttpReader httpReader;
    @MockBean
    private RBCQuoteRepo rbcQuoteRepo;

    List<String> testLines = List.of("USD000000TOD\t2019-09-17\t64.03\t64.44\t63.97\t64.3575\t725278000\t64.184");
    @Test
    void getFromWeb() throws IOException {

        Mockito.when(httpReader.readHttp(Mockito.any())).thenReturn(testLines.stream());
        assert quote.getMaxQuote() == 64.184;
    }

    @Test
    void getFromDatabase() throws IOException {

        Mockito.when(rbcQuoteRepo.findByDate(Mockito.any())).thenReturn(new RBCQuote(LocalDate.now(ZoneId.of("Europe/Moscow")), 100));
        Mockito.when(rbcQuoteRepo.findAll()).thenReturn(List.of(new RBCQuote(LocalDate.now(ZoneId.of("Europe/Moscow")), 100)));
        Mockito.when(httpReader.readHttp(Mockito.any())).thenThrow(new RuntimeException(new IllegalAccessException()));
        assert quote.getMaxQuote() == 100;
    }
}