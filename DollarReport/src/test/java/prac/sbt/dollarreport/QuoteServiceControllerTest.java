package prac.sbt.dollarreport;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import prac.sbt.utils.HttpReader;

import java.util.List;

@SpringBootTest
class QuoteServiceControllerTest {
    @Autowired
    @InjectMocks
    QuoteServiceController quoteServiceController;

    MockMvc mockMvc;

    List<String> testLines = List.of("USD000000TOD\t2019-09-17\t64.03\t64.44\t63.97\t64.3575\t725278000\t64.184");

    @MockBean
    private HttpReader httpReader;

    @Test
    void getMaxQuote() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(quoteServiceController).build();
        Mockito.when(httpReader.readHttp(Mockito.any())).thenReturn(testLines.stream());
        mockMvc.perform(MockMvcRequestBuilders.get("/max"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("64.184"));
    }
}