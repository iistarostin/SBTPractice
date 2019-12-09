package prac.sbt.dollarreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuoteServiceController {
    @Autowired
    Quoter quoter;

    @RequestMapping("/max")
    @ResponseBody
    double getMaxQuote() throws IOException {
        return quoter.getMaxQuote();
    }

    @RequestMapping("/")
    @ResponseBody
    String getAllQuotes() throws IOException {
        return quoter.getAllQuotes().map(Quote::toString).collect(Collectors.joining("<br>\n"));
    }

    @RequestMapping(value = "/json/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<Quote> getAllQuotesJSON() throws IOException {
        return quoter.getAllQuotes().collect(Collectors.toList());
    }

    @RequestMapping("/local")
    @ResponseBody
    String getRepoQuotes() throws IOException {
        return quoter.getQuotesFromRepo().map(Quote::toString).collect(Collectors.joining("<br>\n"));
    }
}
