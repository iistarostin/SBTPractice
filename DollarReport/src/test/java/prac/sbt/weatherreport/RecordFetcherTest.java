package prac.sbt.weatherreport;

import net.aksingh.owmjapis.api.APIException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class RecordFetcherTest {

    @Mock
    OpenWeatherInterface openWeatherInterface;
    @Mock
    RecordRepo recordRepo;

    @InjectMocks
    RecordFetcher recordFetcher;

    List<WeatherRecord> testLines = List.of(new WeatherRecord(LocalDate.now(ZoneId.of("Europe/Moscow")), 64.184));

    @Test
    void getRecordsFromWeb() throws IOException, APIException {
        MockitoAnnotations.initMocks(this);
        when(openWeatherInterface.loadRecords()).thenReturn(testLines.stream());
        Assertions.assertEquals(64.184, recordFetcher.getRecordsFromWeb().findFirst().get().getTemp());
    }

    @Test
    void getFromRepo() throws IOException, APIException {
        MockitoAnnotations.initMocks(this);
        when(recordRepo.findByDate(Mockito.any())).thenReturn(new WeatherRecord(LocalDate.now(ZoneId.of("Europe/Moscow")), 100));
        when(recordRepo.findAll()).thenReturn(List.of(new WeatherRecord(LocalDate.now(ZoneId.of("Europe/Moscow")), 100)));
        when(openWeatherInterface.loadRecords()).thenThrow(new RuntimeException(new IllegalAccessException()));
        Assertions.assertEquals(100, recordFetcher.getAllRecords().findFirst().get().getTemp());
    }
}