package prac.sbt.analysis;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    double temperature;
    double price;

    public Observation(double temperature, double price) {
        this.temperature = temperature;
        this.price = price;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPrice() {
        return price;
    }
}
