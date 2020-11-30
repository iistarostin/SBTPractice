package prac.sbt.weatherreport;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class WeatherRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDate date;
    double temp;

    public LocalDate getDate() {
        return date;
    }

    public double getTemp() {
        return temp;
    }

    public WeatherRecord() {
    }

    public WeatherRecord(LocalDate date, double temp) {
        this.date = date;
        this.temp = temp;
    }

    @Override
    public String toString() {
        return date.toString() + "\t" + String.valueOf(temp);
    }
}
