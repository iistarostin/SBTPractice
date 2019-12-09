package prac.sbt.dollarreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    Quoter quoter;

    @RequestMapping("/")
    @ResponseBody
    double getMaxQuote() throws IOException {
        return quoter.getMaxQuote();
    }

    @RequestMapping("/all")
    @ResponseBody
    String getAllQuotes() throws IOException {
        return quoter.getAllQuotes().map(Quote::toString).collect(Collectors.joining("<br>\n"));
    }

    @RequestMapping("/local")
    @ResponseBody
    String getRepoQuotes() throws IOException {
        return quoter.getQuotesFromRepo().map(Quote::toString).collect(Collectors.joining("<br>\n"));
    }
}
