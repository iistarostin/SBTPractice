package prac.sbt.weatherreport;

import net.aksingh.owmjapis.api.APIException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import net.aksingh.owmjapis.core.OWMPro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OpenWeatherInterface {

    OWMPro owm = new OWMPro("056025d1f78edb92892faec7b05413fb");

    Stream<WeatherRecord> defaultRecords() {
        List<WeatherRecord> records = new ArrayList<>();
        LocalDate day = LocalDate.now();
        for (int i = 0; i < 30; i++) {
            records.add(new WeatherRecord(day, i));
            day = day.minusDays(1);
        }
        return records.stream();
    }

    public Stream<WeatherRecord> loadRecords() throws APIException, IOException {
        String target = "http://api.openweathermap.org/data/2.5/forecast?q=Moscow&units=metric&appid=056025d1f78edb92892faec7b05413fb";
        String JSON =  (new BufferedReader(new InputStreamReader(new URL(target).openConnection().getInputStream()))).lines().collect(Collectors.toList()).get(0);
        return parseOpenWeatherResponse(JSON).stream();
    };

    List<WeatherRecord> parseOpenWeatherResponse(String JSON) {
        JSONObject obj = new JSONObject(JSON);
        JSONArray dataArr = obj.getJSONArray("list");
        List<WeatherRecord> records = new ArrayList<>();
        LocalDate day = LocalDate.now();
        for (int i = 0; i < dataArr.length(); i++) {
             records.add(new WeatherRecord(day, dataArr.getJSONObject(i).getJSONObject("main").getDouble("temp")));
             day = day.minusDays(1);
        }
        return records;
    }
}
