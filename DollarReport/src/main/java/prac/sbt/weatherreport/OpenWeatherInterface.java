package prac.sbt.weatherreport;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.AccumulatedWeatherList;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.WeatherData;
import org.apache.tomcat.jni.SSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.aksingh.owmjapis.model.HistoricalWeatherList;
import net.aksingh.owmjapis.core.OWMPro;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    public Stream<WeatherRecord> loadRecords() throws APIException {
        return defaultRecords();
        /*
        String disabledAlgorithms = Security.getProperty("jdk.tls.disabledAlgorithms");
        Security.setProperty("jdk.tls.disabledAlgorithms", "");
        */
        /*
        try {
            SSLContext.getDefault().createSSLEngine().setEnabledProtocols(new String[] {"TLSv1"});
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
         */
        /*
        System.setProperty("jdk.tls.disabledAlgorithms", "");
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        System.setProperty("jdk.tls.client.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        */
        /*
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        Date oneMonthAgo = calendar.getTime();
        CurrentWeather currentWeather = owm.currentWeatherByCityName("Moscow,RU");
        HistoricalWeatherList data = owm.historicalWeatherByCityName("Moscow,RU", "hour", oneMonthAgo, today);
        return data.getDataList().stream()
                .map((CurrentWeather cw) -> new WeatherRecord(cw.getDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), cw.getMainData().getTemp()));
         */
    };
}
