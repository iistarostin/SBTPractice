package prac.sbt.dollarreport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.stream.Stream;

@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    LocalDate date;
    double value;

    public Quote(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    public Quote() { }

    public LocalDate getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return date.toString() + "\t" + String.valueOf(value);
    }
}
