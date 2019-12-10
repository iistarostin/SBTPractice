package prac.sbt.weatherreport;

import net.aksingh.owmjapis.api.APIException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherInterfaceTest {
    @Autowired
    OpenWeatherInterface openWeather = new OpenWeatherInterface();
    @Test
    void loadRecords() throws APIException {
        Stream<WeatherRecord> records = openWeather.loadRecords();

    }
}