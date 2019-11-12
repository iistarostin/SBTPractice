package prac.sbt.dollarreport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class RBCQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    LocalDate date;
    double value;

    public RBCQuote(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    public RBCQuote() {
    }

    public LocalDate getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

}
