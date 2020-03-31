package prac.sbt.analysis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "app.network")
public class NetworkProperties {
    private String WeatherServiceURL = "127.0.0.1:8092";
    private String DollarServiceURL = "127.0.0.1:8093";

    public String getWeatherServiceURL() {
        return WeatherServiceURL;
    }

    public void setWeatherServiceURL(String weatherServiceURL) {
        WeatherServiceURL = weatherServiceURL;
    }

    public String getDollarServiceURL() {
        return DollarServiceURL;
    }

    public void setDollarServiceURL(String dollarServiceURL) {
        DollarServiceURL = dollarServiceURL;
    }
}
