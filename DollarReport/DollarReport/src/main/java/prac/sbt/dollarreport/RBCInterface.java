package prac.sbt.dollarreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prac.sbt.utils.HttpReader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Component
public class RBCInterface {
    @Autowired
    HttpReader httpReader;

    public Stream<Quote> getQuotesFromRBC() throws IOException {
        String target = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers=USD000000TOD&separator=TAB&data_format=BROWSER&lastdays=30";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return httpReader.readHttp(target)
                    .map((String s) -> s.split("\t"))
                    .map((String[] s) -> new Quote(LocalDate.parse(s[1], dateTimeFormatter), Double.parseDouble(s[7])));
        }
        catch (IOException e) {
            return Stream.of(   new Quote(LocalDate.parse("2020-05-19", dateTimeFormatter), 75),
                                new Quote(LocalDate.parse("2020-05-20", dateTimeFormatter), 75),
                                new Quote(LocalDate.parse("2020-05-21", dateTimeFormatter), 75),
                                new Quote(LocalDate.parse("2020-05-22", dateTimeFormatter), 75),
                                new Quote(LocalDate.parse("2020-05-25", dateTimeFormatter), 75));
        }
    }
}
