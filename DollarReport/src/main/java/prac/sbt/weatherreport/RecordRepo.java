package prac.sbt.weatherreport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import prac.sbt.dollarreport.Quote;

import java.time.LocalDate;

@Repository
public interface RecordRepo extends CrudRepository<WeatherRecord, Long> {
    WeatherRecord findByDate(LocalDate date);
}

