package prac.sbt.analysis;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MLModel {

    public double getSignificance(List<Observation> data) {
        SimpleRegression simpleRegression = new SimpleRegression();
        for (Observation observation : data) {
            simpleRegression.addData(observation.temperature, observation.price);
        }
        simpleRegression.regress();
        return simpleRegression.getSignificance();
    }
}
