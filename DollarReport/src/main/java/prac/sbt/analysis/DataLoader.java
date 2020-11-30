package prac.sbt.analysis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prac.sbt.weatherreport.WeatherRecord;
import prac.sbt.dollarreport.Quote;
import prac.sbt.utils.HttpReader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader {
    @Autowired
    NetworkProperties networkProperties;
    @Autowired
    HttpReader httpReader;
    @Autowired
    ObjectMapper objectMapper;
    public DataLoader() {
        httpReader = new BulkHTTPReader();
    }
    Stream<Quote> loadQuotes() throws IOException {
        String json = httpReader.readHttp(networkProperties.getDollarServiceURL().concat("/json/all")).collect(Collectors.joining());
        return objectMapper.readValue(json, new TypeReference<List<Quote>>(){}).stream();
    }
    Stream<WeatherRecord> loadRecords() throws IOException {
        String json = httpReader.readHttp(networkProperties.getWeatherServiceURL().concat("/json/all")).collect(Collectors.joining());
        return objectMapper.readValue(json, new TypeReference<List<WeatherRecord>>() {
        }).stream();
    }
    public Stream<Observation> getData() throws IOException {
        Stream<Quote> quotes = loadQuotes();
        Stream<WeatherRecord> records = loadRecords();
        Map<LocalDate, Double> quoteMap = quotes.collect(Collectors.toMap(Quote::getDate, Quote::getValue));
        return records.filter(wr -> quoteMap.containsKey(wr.getDate())).map(wr -> new Observation(wr.getTemp(), quoteMap.get(wr.getDate())));
    }
}
