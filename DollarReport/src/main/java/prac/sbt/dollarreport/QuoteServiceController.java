package prac.sbt.dollarreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuoteServiceController {
    @Autowired
    Quoter quoter;

    @GetMapping("/max")
    @ResponseBody
    double getMaxQuote() throws IOException {
        return quoter.getMaxQuote();
    }

    @GetMapping("/")
    @ResponseBody
    public String getAllQuotes() throws IOException {
        return quoter.getAllQuotes().map(Quote::toString).collect(Collectors.joining("<br>\n"));
    }

    @RequestMapping(value = "/json/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Quote> getAllQuotesJSON() throws IOException {
        return quoter.getAllQuotes().collect(Collectors.toList());
    }

    @GetMapping("/local")
    @ResponseBody
    public String getRepoQuotes() throws IOException {
        return quoter.getQuotesFromRepo().map(Quote::toString).collect(Collectors.joining("<br>\n"));
    }
}
