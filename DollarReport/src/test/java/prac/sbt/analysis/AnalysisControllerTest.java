package prac.sbt.analysis;

import org.apache.commons.math3.util.Pair;
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
import prac.sbt.weatherreport.OpenWeatherInterface;
import prac.sbt.weatherreport.WeatherRecord;

import javax.management.ObjectName;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnalysisControllerTest {
    @Autowired
    @InjectMocks
    AnalysisController analysisController;

    MockMvc mockMvc;

    List<Observation> testLines = List.of(new Observation(0.0, 0.0), new Observation(1.0, 1.0), new Observation(2.0, 2.0));

    @MockBean
    private DataLoader dataLoader;

    @Test
    void getSignificance() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(analysisController).build();
        Mockito.when(dataLoader.getData()).thenReturn(testLines.stream());
        mockMvc.perform(MockMvcRequestBuilders.get("/sig"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0.0"));
    }

}