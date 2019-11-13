package prac.sbt.dollarreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class Quoter {

    @Autowired
    ExternalServiceInterface externalServiceInterface;
    @Autowired
    QuoteRepo quoteRepo;


    public double getMaxQuote() throws IOException {
        return getAllQuotes().map(Quote::getValue).max(Double::compareTo).get();
    }

    public Stream<Quote> getAllQuotes() throws IOException {
        if (quotesInRepoUpToDate()) {
            return getQuotesFromRepo();
        }
        return updateDatabase(getQuotesFromWeb());
    }

    Stream<Quote> getQuotesFromWeb() throws IOException {
        return externalServiceInterface.getQuotesFromRBC();
    }

    boolean quotesInRepoUpToDate() {
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        Quote todayQuote = quoteRepo.findByDate(today);
        return todayQuote != null;
    }

    public Stream<Quote> getQuotesFromRepo() throws IOException {
        return StreamSupport.stream(quoteRepo.findAll().spliterator(),false);
    }

    Stream<Quote> updateDatabase(Stream<Quote> newQuotes)
    {
        quoteRepo.deleteAll();
        return newQuotes.peek((Quote quote) -> quoteRepo.save(quote));
    }
}
