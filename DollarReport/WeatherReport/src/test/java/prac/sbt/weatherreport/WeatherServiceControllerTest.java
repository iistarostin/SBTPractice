package prac.sbt.weatherreport;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import org.junit.jupiter.api.Test;

@SpringBootTest
class WeatherServiceControllerTest {
    @Autowired
    @InjectMocks
    WeatherServiceController weatherServiceController;

    MockMvc mockMvc;

    List<WeatherRecord> testLines = List.of();

    @MockBean
    private OpenWeatherInterface openWeatherInterface;

    @Test
    void getMaxQuote() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(weatherServiceController).build();
        Mockito.when(openWeatherInterface.loadRecords()).thenReturn(testLines.stream());
        mockMvc.perform(MockMvcRequestBuilders.get("/json/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}