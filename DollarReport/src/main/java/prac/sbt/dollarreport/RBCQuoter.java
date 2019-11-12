package prac.sbt.dollarreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
@Controller
public class RBCQuoter {

    @Autowired
    HttpReader httpReader;
    @Autowired
    RBCQuoteRepo rbcQuoteRepo;

    @RequestMapping("/")
    @ResponseBody
    public double getMaxQuote() throws IOException {
        return loadQuotes().map(RBCQuote::getValue).max(Double::compareTo).get();
    }
    Stream<RBCQuote> loadQuotes() throws IOException {
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        RBCQuote todayQuote = rbcQuoteRepo.findByDate(today);
        if (todayQuote != null) {
            return StreamSupport.stream(rbcQuoteRepo.findAll().spliterator(),false);
        }
        else {
            return getQuotesFromWeb().map((RBCQuote quote) -> {
                rbcQuoteRepo.save(quote);
                return quote;
            });
        }
    }

    Stream<RBCQuote> getQuotesFromWeb() throws IOException {
        String target = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers=USD000000TOD&separator=TAB&data_format=BROWSER&lastdays=30";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return httpReader.readHttp(target)
                .map((String s) -> s.split("\t"))
                .map((String[] s) -> new RBCQuote(LocalDate.parse(s[1], dateTimeFormatter), Double.parseDouble(s[7])));
    }

}
