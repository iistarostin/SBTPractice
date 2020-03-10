package prac.sbt.weatherreport;


import net.aksingh.owmjapis.api.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class RecordFetcher {
    @Autowired
    RecordRepo repo;
    @Autowired
    OpenWeatherInterface openWeather;
    public Stream<WeatherRecord> getAllRecords() throws APIException, IOException {
        if (quotesInRepoUpToDate()) {
            return getRecordsFromRepo();
        }
        return updateDatabase(getRecordsFromWeb());
    }

    public Stream<WeatherRecord> getRecordsFromWeb() throws APIException, IOException {
        return openWeather.loadRecords();
    }

    public boolean quotesInRepoUpToDate() {
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        WeatherRecord todayRecord = repo.findByDate(today);
        return todayRecord != null;
    }

    public Stream<WeatherRecord> getRecordsFromRepo() {
        return StreamSupport.stream(repo.findAll().spliterator(),false);
    }

    public Stream<WeatherRecord> updateDatabase(Stream<WeatherRecord> newQuotes)
    {
        repo.deleteAll();
        return newQuotes.peek(quote -> repo.save(quote));
    }
}
