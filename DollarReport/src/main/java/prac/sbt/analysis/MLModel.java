package prac.sbt.analysis;

import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class MLModel {

    public double getSignificance(List<Pair<Double, Double>> data) {
        SimpleRegression simpleRegression = new SimpleRegression();
        for (Pair<Double, Double> datum : data) {
            simpleRegression.addData(datum.getFirst(), datum.getSecond());
        }
        simpleRegression.regress();
        return simpleRegression.getSignificance();
    }
}
