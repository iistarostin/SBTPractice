package prac.sbt.dollarreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prac.sbt.utils.HttpReader;
import prac.sbt.utils.SimpleHttpReader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Component
public class RBCInterface {
    HttpReader httpReader;
    public RBCInterface() {
        httpReader = new SimpleHttpReader();
    }
    public Stream<Quote> getQuotesFromRBC() throws IOException {
        String target = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers=USD000000TOD&separator=TAB&data_format=BROWSER&lastdays=30";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return httpReader.readHttp(target)
                .map((String s) -> s.split("\t"))
                .map((String[] s) -> new Quote(LocalDate.parse(s[1], dateTimeFormatter), Double.parseDouble(s[7])));
    }
}
